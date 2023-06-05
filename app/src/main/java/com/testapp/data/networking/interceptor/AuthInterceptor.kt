package com.testapp.data.networking.interceptor

import com.testapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
            .newBuilder()
            .addHeader("private-key", BuildConfig.privateKey)
            .build()
        return chain.proceed(req)

    }
}