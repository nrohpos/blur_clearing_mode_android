package com.nrohpos.blurviewclearing.extension

import android.view.ViewGroup
import androidx.core.view.children
import com.nrohpos.blurviewclearing.base.BaseActivity

fun BaseActivity.removeBlurView() {
    if (this.rootView != null && this.rootView is ViewGroup) {
        val layout = (this.rootView as ViewGroup)
        var pos: Int? = null
        layout.children.forEachIndexed { index, view ->
            if (view.tag != null && view.tag.toString() == blurTag) {
                pos = index
                return@forEachIndexed
            }
        }
        pos?.let {
            layout.removeViewAt(it)
        }
    }
}

fun BaseActivity.addBlurView() {
    this.removeBlurView() // in case the old one

    if (this.rootView != null && this.rootView is ViewGroup) {
        (this.rootView as ViewGroup).addView(blurLayout)
    }

}