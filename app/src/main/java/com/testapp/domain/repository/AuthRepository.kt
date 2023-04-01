package com.testapp.domain.repository


import com.testapp.data.networking.AppServices
import com.testapp.data.networking.dto.Categories
import com.testapp.data.networking.dto.OptionsDto
import com.testapp.data.networking.dto.SubCategoryDto
import com.testapp.data.networking.wrappers.unwrapResponse
import com.testapp.domain.state.StateData
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val appServices: AppServices
) {

    suspend fun getCategories(): StateData<Categories> {
        val categoriesResponse = unwrapResponse(appServices.getAllCategories())
        return StateData<Categories>().success(categoriesResponse)
    }


    suspend fun getSubCategories(catId: Int): StateData<List<SubCategoryDto>> {
        val subCategoriesResponse = unwrapResponse(appServices.getSubCategories(13))
        return StateData<List<SubCategoryDto>>().success(subCategoriesResponse)
    }

    suspend fun getOptions(option: Int): StateData<List<OptionsDto>> {
        val optionsResponse = unwrapResponse(appServices.getOptions(53))
        return StateData<List<OptionsDto>>().success(optionsResponse)
    }
}