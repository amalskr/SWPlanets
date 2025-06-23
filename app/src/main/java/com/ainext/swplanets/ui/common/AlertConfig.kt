package com.ainext.swplanets.ui.common

data class AlertConfig(
    val title: String,
    val message: String,
    val positiveButtonText: String = "OK",
    val negativeButtonText: String? = null,
    val onPositiveClick: (() -> Unit)? = null,
    val onNegativeClick: (() -> Unit)? = null
)
