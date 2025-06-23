package com.ainext.swplanets.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpacerUp(size: Dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun SpacerSide(size: Dp) {
    Spacer(modifier = Modifier.width(size))
}