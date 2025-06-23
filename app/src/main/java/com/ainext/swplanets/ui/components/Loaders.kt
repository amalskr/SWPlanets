package com.ainext.swplanets.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallLoader() {
    CircularProgressIndicator(
        modifier = Modifier.size(24.dp),
        strokeWidth = 2.dp,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun NormalLoader() {
    CircularProgressIndicator()
}