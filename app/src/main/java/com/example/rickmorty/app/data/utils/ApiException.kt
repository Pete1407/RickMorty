package com.example.rickmorty.app.data.utils

import okio.IOException

class ApiException(message : String) : IOException(message) {
}