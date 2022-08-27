package com.example.rickmorty.app.data.model

data class ApiResponse(
    var data: List<Character> = arrayListOf(),
    var errorObj: Throwable? = null
)
{
    fun setApiResponseMain(value : List<Character>){
        this.data = value
        this.errorObj = null
    }

    fun setErrorValueMain(error: Throwable?) {
        this.errorObj = error
        this.data = arrayListOf()
    }

    fun getApiResponse():List<Character>{
        return this.data
    }

    fun setApiResponse(value : List<Character>){
        this.data = value
    }

    fun getError():Throwable?{
        return errorObj
    }

    fun setError(error: Throwable?) {
        this.errorObj = error
    }
}