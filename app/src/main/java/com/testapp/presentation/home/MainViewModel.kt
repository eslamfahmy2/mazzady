package com.testapp.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.data.networking.dto.Category
import com.testapp.data.networking.dto.SubCategoryDto
import com.testapp.domain.repository.AuthRepository
import com.testapp.domain.state.AppOperationState
import com.testapp.domain.state.CategoriesScreenState
import com.testapp.utils.safeLaunchWithFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {


    private val safeLaunchFlow = MutableStateFlow(AppOperationState())
    private val categories = mutableStateListOf<Category>()
    private val options = mutableStateListOf<SubCategoryDto>()

    private val _homeScreenStateFlow =
        MutableStateFlow<CategoriesScreenState>(CategoriesScreenState.Loading)

    private val slCategory = mutableStateOf<Category?>(null)
    private val slSubCategory = mutableStateOf<Category?>(null)

    fun setSubCategory(category: Category) {
        viewModelScope.safeLaunchWithFlow(safeLaunchFlow) {
            slSubCategory.value = category
            category.id?.let {
                authRepository.getSubCategories(it).data?.let { data ->
                    options.clear()
                    options.addAll(data)
                    _homeScreenStateFlow.value = homeSuccessState()
                }
            }
        }
    }

    private fun homeSuccessState() = CategoriesScreenState.Success(
        categories = categories,
        subCategories = slCategory.value?.children,
        options = options,
    )


    fun setCategory(category: Category) {
        viewModelScope.safeLaunchWithFlow(safeLaunchFlow) {
            slCategory.value = category
            _homeScreenStateFlow.value = homeSuccessState()
        }
    }

    fun onOptionSelected(category: SubCategoryDto, other: Boolean, selectedOption: String) {
        viewModelScope.safeLaunchWithFlow(safeLaunchFlow) {
            val new = options.map {
                if (it.id == category.id) {
                    it.copy(selectedOption = selectedOption, isOther = other)
                } else
                    it
            }
            options.clear()
            options.addAll(new)
            _homeScreenStateFlow.value = homeSuccessState()
        }
    }


    // The UI collects from this StateFlow to get its state updates
    val mainScreenState: StateFlow<CategoriesScreenState> =
        safeLaunchFlow.flatMapLatest { operationState: AppOperationState ->
            screenStateFlow(operationState)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = CategoriesScreenState.Loading
        )

    private fun screenStateFlow(operationState: AppOperationState): MutableStateFlow<CategoriesScreenState> {
        when (operationState.status) {
            AppOperationState.DataStatus.ERROR -> {
                _homeScreenStateFlow.value =
                    CategoriesScreenState.Error(operationState.error?.message)
            }
            AppOperationState.DataStatus.LOADING -> {
                _homeScreenStateFlow.value =
                    CategoriesScreenState.Loading
            }
            AppOperationState.DataStatus.CREATED -> {}
            AppOperationState.DataStatus.SUCCESS -> {}
            AppOperationState.DataStatus.COMPLETE -> {}
        }
        return _homeScreenStateFlow
    }

    fun getCategories() {
        viewModelScope.safeLaunchWithFlow(safeLaunchFlow) {
            authRepository.getCategories().data?.let {
                val list = it.categories ?: listOf()
                categories.clear()
                categories.addAll(list)
                _homeScreenStateFlow.value = homeSuccessState()
            }
        }
    }

    fun getTableData(): List<Pair<String, String?>> {
        val listOfPairs = listOf<Pair<String, String?>>().toMutableList()
        val cat = Pair("Category", slCategory.value?.name)
        val sub = Pair("Sub Category", slSubCategory.value?.name)
        listOfPairs.add(cat)
        listOfPairs.add(sub)
        options.forEach {
            val option = Pair(it.name ?: "", it.selectedOption)
            listOfPairs.add(option)
        }
        return listOfPairs.toList()
    }

}