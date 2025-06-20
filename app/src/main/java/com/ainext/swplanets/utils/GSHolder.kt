package com.ainext.swplanets.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ainext.swplanets.domain.Planet
import kotlinx.serialization.Serializable

/*
* GlobalState content can handle everywhere
* */
object GSHolder {
    var mState by mutableStateOf(GlobalState())
        private set
}

@Serializable
data class GlobalState(
    var planetList: MutableState<List<Planet>> = mutableStateOf(emptyList()),
)
