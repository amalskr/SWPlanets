package com.ainext.swplanets.utils.extensions

fun String.capitalizeFirst(): String = replaceFirstChar { it.uppercaseChar() }

val Int.isEven: Boolean get() = this % 2 == 0