package com.testapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.testapp.R
import com.testapp.data.networking.dto.Category
import com.testapp.data.networking.dto.Option
import com.testapp.data.networking.dto.SubCategoryDto
import com.testapp.domain.state.CategoriesScreenState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(
    uiState: CategoriesScreenState,
    onSubmit: () -> Unit,
    onCategorySelected: (Category) -> Unit,
    onSubCategorySelected: (Category) -> Unit,
    onOptionSelected: (SubCategoryDto, Boolean, String, Boolean) -> Unit,
) {

    val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val searchableOptions = remember {
        mutableStateOf<SubCategoryDto?>(null)
    }
    val bottomSheetVisible = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(bottomSheetVisible.value) {
        if (bottomSheetVisible.value) {
            scaffoldState.bottomSheetState.animateTo(BottomSheetValue.Expanded)
        } else {
            scaffoldState.bottomSheetState.animateTo(BottomSheetValue.Collapsed)
        }
    }

    BottomSheetScaffold(
        sheetContent = {
            PopupOptionsItem(
                title = searchableOptions.value?.name ?: "",
                options = searchableOptions.value?.options ?: listOf(),
                onClose = {
                    bottomSheetVisible.value = false
                },
                onOptionSelected = { other, option, hasChild ->
                    searchableOptions.value?.let {
                        onOptionSelected(it, other, option, hasChild)
                    }
                },
            )
        },
        scaffoldState = scaffoldState,
        sheetElevation = 0.dp,
        sheetPeekHeight = 0.dp,
        backgroundColor = Color.White,
        modifier = Modifier.testTag("test_tag_home_screen")
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is CategoriesScreenState.Error -> {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        text = uiState.msg ?: "-",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary
                    )
                }
                CategoriesScreenState.Loading -> {
                    CircularProgressIndicator()
                }
                is CategoriesScreenState.Success -> {

                    val categories = uiState.categories
                    val subCategories = uiState.subCategories
                    val options = uiState.options

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen._16sdp))
                            .verticalScroll(rememberScrollState())
                    ) {

                        categories.let { mainCategories ->
                            OutLineDropdownMenuItem(
                                title = "Main Category",
                                options = mainCategories,
                                onSelectItem = onCategorySelected,
                                selectedOption = mainCategories.first().name ?: ""
                            )
                            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
                        }

                        val sub = subCategories?.map { Category(name = it.name, id = it.id) }
                        if (sub != null && sub.isNotEmpty()) {
                            OutLineDropdownMenuItem(
                                title = "Sub Category",
                                options = sub,
                                onSelectItem = onSubCategorySelected,
                                selectedOption = sub.first().name ?: ""
                            )
                            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
                        }

                        options?.let {

                            it.forEach { mainOptions ->
                                OutLineMenuItem(
                                    title = mainOptions.slug ?: "",
                                    onSelectItem = {
                                        searchableOptions.value = mainOptions
                                        bottomSheetVisible.value = true
                                    },
                                    selectedOption = mainOptions.selectedOption ?: "",
                                    isOther = mainOptions.isOther,
                                    onOtherSelected = { it ->
                                        onOptionSelected(mainOptions, true, it, false)
                                    }
                                )
                                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
                            }


                            if (it.isNotEmpty()) {
                                Button(
                                    onClick = onSubmit,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("test_tag_go_to_table")
                                ) {
                                    Text(text = "Submit", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
            com.testapp.presentation.components.Scrim(
                onDismiss = {
                    bottomSheetVisible.value = false
                },
                visible = scaffoldState.bottomSheetState.targetValue != BottomSheetValue.Collapsed
            )
        }
    }
}


@Composable
fun OutLineDropdownMenuItem(
    title: String,
    options: List<Category>,
    onSelectItem: (Category) -> Unit,
    selectedOption: String
) {
    val expanded = remember { mutableStateOf(false) }
    val icon = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown
    val focusManager = LocalFocusManager.current

    Column {
        OutlinedTextField(
            value = selectedOption,
            readOnly = true,
            onValueChange = {},
            trailingIcon = {
                Icon(icon, null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    expanded.value = it.isFocused
                },
            label = { Text(title) },
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                focusManager.clearFocus()
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            options.forEach { label ->
                DropdownMenuItem(onClick = {
                    onSelectItem(label)
                    expanded.value = false
                    focusManager.clearFocus()
                }) {
                    Text(text = label.name ?: "")
                }
            }
        }
    }
}

@Composable
fun OutLineMenuItem(
    title: String,
    onSelectItem: (String) -> Unit,
    onOtherSelected: (String) -> Unit,
    selectedOption: String,
    isOther: Boolean
) {
    val searchText = remember { mutableStateOf(selectedOption) }
    val icon = Icons.Filled.ArrowDropDown

    OutlinedTextField(
        value = if (isOther) "Other" else selectedOption,
        readOnly = true,
        onValueChange = {},
        trailingIcon = {
            Icon(icon, contentDescription = "down", modifier = Modifier
                .wrapContentWidth()
                .clickable { onSelectItem(searchText.value) })
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.hasFocus)
                    onSelectItem(searchText.value)
            },
        label = { Text(title) },
    )

    Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))

    if (isOther) {
        OutlinedTextField(
            value = searchText.value,
            readOnly = false,
            onValueChange = {
                searchText.value = it
                // onSelectItem(searchText.value)
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Specify here") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onOtherSelected(searchText.value)
                }
            ),
        )
    }
}


@Composable
fun PopupOptionsItem(
    title: String,
    options: List<Option>,
    onClose: () -> Unit,
    onOptionSelected: (Boolean, String, Boolean) -> Unit
) {

    var searchText = ""
    val filteredList =
        options.filter { it.name?.lowercase()?.contains(searchText.lowercase()) == true }
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen._8sdp),
            topEnd = dimensionResource(id = R.dimen._8sdp)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen._16sdp))
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.Center)
                )
                IconButton(
                    onClick = onClose,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "close")
                }
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(30),
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = "Search",
                        color = MaterialTheme.colors.onPrimary
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "search")
                },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.body1.fontSize
                ),
                singleLine = true,
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchText = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.White.copy(alpha = ContentAlpha.medium),
                    focusedIndicatorColor = MaterialTheme.colors.primary,
                    unfocusedIndicatorColor = MaterialTheme.colors.primary
                )
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
            Text(
                text = "Other",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onOptionSelected(true, "", false)
                        onClose()
                    }
                    .padding(dimensionResource(id = R.dimen._8sdp))
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = dimensionResource(id = R.dimen._1sdp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
            filteredList.forEach { label ->
                Text(
                    text = label.name ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onOptionSelected(false, label.name ?: "", label.child ?: false)
                            onClose()
                        }
                        .padding(dimensionResource(id = R.dimen._8sdp))
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = dimensionResource(id = R.dimen._1sdp),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))
        }
    }

}