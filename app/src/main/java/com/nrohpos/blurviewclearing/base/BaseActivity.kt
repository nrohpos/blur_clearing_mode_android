package com.nrohpos.blurviewclearing.base

import android.content.Intent
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.nrohpos.blurviewclearing.data.DataOperations
import com.nrohpos.blurviewclearing.extension.addBlurView
import com.nrohpos.blurviewclearing.extension.removeBlurView
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    internal val blurTag = UUID.randomUUID().toString()
    private var triggeredOnPause = false

    internal val blurLayout: LinearLayout by lazy {
        val blur = LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.GRAY)
            alpha = 0.9f
            tag = blurTag
        }
        return@lazy blur
    }


    public override fun onResume() {
        super.onResume()
        val operations = DataOperations.sharedDataOperations()

        if (operations.displayLock) {
            this.addBlurView()
        } else {
            this.removeBlurView()
        }
        triggeredOnPause = false
    }

    public override fun onPause() {
        if (!triggeredOnPause) {
            val operations: DataOperations = DataOperations.sharedDataOperations()
            operations.lostFocusDate = Date()
            this.addBlurView()
        }
        super.onPause()

    }

    override fun startActivity(intent: Intent) {
        triggeredOnPause = true
        super.startActivity(intent)
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        triggeredOnPause = true
        super.startActivityForResult(intent, requestCode)
    }

}