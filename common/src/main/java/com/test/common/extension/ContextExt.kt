package com.test.common.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

inline fun Context.inflate(res: Int, parent: ViewGroup? = null): View {
    return LayoutInflater.from(this).inflate(res, parent , false)
}
