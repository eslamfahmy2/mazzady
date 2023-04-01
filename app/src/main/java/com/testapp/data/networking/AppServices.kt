package com.testapp.data.networking

import com.testapp.data.networking.dto.Categories
import com.testapp.data.networking.dto.OptionsDto
import com.testapp.data.networking.dto.SubCategoryDto
import com.testapp.data.networking.wrappers.WsResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppServices {


    @GET("get_all_cats")
    suspend fun getAllCategories(): Response<WsResponseWrapper<Categories>>

    @GET("properties")
    suspend fun getSubCategories(@Query("cat") cat: Int): Response<WsResponseWrapper<List<SubCategoryDto>>>

    @GET("get-options-child/{option}")
    suspend fun getOptions(@Path("option") option: Int): Response<WsResponseWrapper<List<OptionsDto>>>

}