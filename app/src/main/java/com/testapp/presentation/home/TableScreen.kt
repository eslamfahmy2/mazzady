package com.testapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.testapp.R


@Composable
fun TableScreen(
    options: List<Pair<String, String?>>,
    onNavigateNext: () -> Unit
) {

    val mainViewModel: MainViewModel = hiltViewModel()
    mainViewModel.getTableData()
    // Each cell of a column must have the same weight.
    val column1Weight = .5f
    val column2Weight = .5f
    // The LazyColumn will be our table. Notice the use of the weights below
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(dimensionResource(id = R.dimen._16sdp))
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TableCell(text = "Name", weight = column1Weight)
            TableCell(text = "Value", weight = column2Weight)
        }
        options.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(text = it.first, weight = column1Weight)
                TableCell(text = it.second ?: "", weight = column2Weight)
            }
        }

        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp)))

        Button(
            onClick = onNavigateNext,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("test_tag_go_to_details")
        ) {
            Text(text = "Details", color = Color.White)
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Box(
        modifier = Modifier
            .weight(weight)
            .defaultMinSize(minHeight = dimensionResource(id = R.dimen._55sdp))
            .border(dimensionResource(id = R.dimen._1sdp), Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}


/*
@Composable
fun TableScreen(
    options: List<SubCategoryDto>
) {

    options.forEach { item ->
        TableCell(text = item.name ?: "")
    }

}

@Composable
fun TableCell(
    text: String,
) {
    Text(
        text = text,
        Modifier
            .border(dimensionResource(id = R.dimen._1sdp), Color.Black)
            .size(dimensionResource(id = R.dimen._20sdp))
            .padding(dimensionResource(id = R.dimen._8sdp))
    )
}
*/
