package com.ainext.swplanets.ui.common

import android.widget.Toast

enum class ToastDuration(val value: Int) {
    SHORT(Toast.LENGTH_SHORT),
    LONG(Toast.LENGTH_LONG)
}