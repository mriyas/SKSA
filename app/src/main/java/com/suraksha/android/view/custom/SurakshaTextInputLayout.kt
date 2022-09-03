package com.suraksha.android.view.custom

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class SurakshaTextInputLayout : TextInputLayout {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    )

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        clearEditTextColorfilter()
    }

    override fun setError(error: CharSequence?) {
        super.setError(error)
        clearEditTextColorfilter()
    }

    private fun clearEditTextColorfilter() {
        val editText = editText
        if (editText != null) {
            val background = editText.background
            background?.clearColorFilter()
        }
    }
}