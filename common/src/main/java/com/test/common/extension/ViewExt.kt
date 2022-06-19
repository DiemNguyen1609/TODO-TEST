package com.test.common.extension

import android.os.SystemClock
import android.util.Log
import android.view.View


    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            try {
                onSafeClick(it)
            } catch (e: java.lang.Exception) {
                Log.wtf("EX", e)
            }
        }
        setOnClickListener(safeClickListener)
    }

    class SafeClickListener(
        private var defaultInterval: Int = 1000,
        private val onSafeCLick: (View) -> Unit
    ) : View.OnClickListener {
        private var lastTimeClicked: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
                return
            }
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeCLick(v)
        }
    }
