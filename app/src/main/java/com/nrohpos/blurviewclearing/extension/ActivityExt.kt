package com.nrohpos.blurviewclearing.extension

import android.app.Activity
import android.view.View

val Activity.rootView: View?
    get() {
        return window.decorView.findViewById(android.R.id.content)
    }
