package com.example.rickmorty.app.data.utils

sealed class Resource<T>(val data : T? = null,val message : String? = null) {

    class Loading<T>(data:T? = null):Resource<T>(data)

    class Success<T>(data: T? = null,msg:String?=null):Resource<T>(data,msg)

    class Error<T>(msg: String? = null,data: T? = null):Resource<T>(data,msg)


}