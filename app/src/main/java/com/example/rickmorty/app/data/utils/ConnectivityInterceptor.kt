package com.example.rickmorty.app.data.utils

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class ConnectivityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!WifiService.instance.isOnline()) {
            throw IOException("No internet connection")
        } else {
            return chain.proceed(chain.request())
        }
    }


}