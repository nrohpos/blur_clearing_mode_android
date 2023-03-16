package com.nrohpos.blurviewclearing.data

import java.util.*


class DataOperations {
    var lostFocusDate: Date? = null

    val displayLock: Boolean
        get() {
            return if (lostFocusDate != null) {
                val now = Date()
                val nowMS = now.time
                val lostFocusMS = lostFocusDate!!.time
                val minutesPassed = (nowMS - lostFocusMS).toInt() / 60000

                lostFocusDate = null
                minutesPassed >= 1
            } else {
                false
            }
        }

    companion object {
        private lateinit var instant: DataOperations

        fun sharedDataOperations(): DataOperations {
            if (!this::instant.isInitialized) {
                instant = DataOperations()
            }
            return instant
        }
    }
}