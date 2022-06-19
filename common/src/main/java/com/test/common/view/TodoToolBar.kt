package com.test.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.test.common.R
import com.test.common.extension.setSafeOnClickListener
import com.test.common.view.`interface`.IToolbar
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class TodoToolBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IToolbar {

    private var title: String = ""
        set(value) {
            toolbarTitleTxv.text = value
            field = value
        }

    var backBtnClickListener: () -> Unit = {}

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this, true)
        initAttrs(context, attrs)
        initUI()
        initControl()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val typedArr = context.obtainStyledAttributes(attrs, R.styleable.TodoToolbar, 0, 0).apply {
            try {
                title = getString(R.styleable.TodoToolbar_toolbar_title) ?: ""
            } catch (e: Exception) {
            }
        }
        typedArr.recycle()
    }

    private fun initUI() {

    }

    private fun initControl() {
        tvBack.setSafeOnClickListener {
            backBtnClickListener.invoke()
        }

    }

    override fun setToolBarTitle(text: String) {
        title = text
    }

    override fun setOnBackButtonClickListener(listener: () -> Unit) {
        backBtnClickListener = listener
    }
}