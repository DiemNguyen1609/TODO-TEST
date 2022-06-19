package com.test.common.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.test.common.R

public class LoadingDialog {

    private lateinit var dialog: Dialog
    private lateinit var context: Context

    constructor(context: Context?) {
        if (context != null) {
            this.context = context
        }
    }

    fun showLoadingDialog() {
        dialog = Dialog(context)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.create()
        dialog.show()
    }

    fun hideLoadingDialog() {
        dialog.dismiss()
    }

}