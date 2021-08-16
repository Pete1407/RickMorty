package com.example.rickmorty.app.data.utils

sealed class Resource<T>(val data : T? = null,val message : String? = null) {

    class Loading<T>(data:T):Resource<T>(data)

    class Success<T>(data: T? = null,val msg:String?=null):Resource<T>()

    class Error<T>(msg: String? = null,data: T?):Resource<T>()


}