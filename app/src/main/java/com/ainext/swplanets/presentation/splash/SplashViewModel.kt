package com.ainext.swplanets.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainext.swplanets.utils.NetworkObserver
import kotlinx.coroutines.launch

class SplashViewModel(private val networkObs: NetworkObserver) : ViewModel() {

    fun onLoad() {
        viewModelScope.launch {
            println("Called ViewModel :" + networkObs.isNetworkAvailable())
        }
    }
}