package com.example.rickmorty.app.data.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>(data:T? = null):Resource<T>()

    class Success<T>(result: T? = null):Resource<T>(data = result)

    class Error<T>(msg: String = ""):Resource<T>(message = msg)


}