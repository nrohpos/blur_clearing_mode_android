package com.nrohpos.blurviewclearing.extension

import android.content.Context

val Context.screenWidth: Int
    get() {
        val displayMetrics = resources.displayMetrics
        return displayMetrics.widthPixels
    }

val Context.screenHeight: Int
    get() {
        val displayMetrics = resources.displayMetrics
        return displayMetrics.heightPixels
    }