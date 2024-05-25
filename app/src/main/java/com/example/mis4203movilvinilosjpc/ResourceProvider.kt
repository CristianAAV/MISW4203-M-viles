package com.example.mis4203movilvinilosjpc

import android.content.Context
import androidx.annotation.StringRes

object ResourceProvider {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}