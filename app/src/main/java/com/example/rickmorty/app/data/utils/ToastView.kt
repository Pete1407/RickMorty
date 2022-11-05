package com.example.rickmorty.app.data.utils

import android.content.Context
import android.widget.Toast

class ToastView(private val context: Context) {
    fun showShortToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}