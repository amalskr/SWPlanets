package com.ainext.swplanets.ui.common

import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import com.ainext.swplanets.SwPlanetsApp.Companion.context
import com.ainext.swplanets.SwPlanetsApp.Companion.currentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppNotifier {

    // Show Toast
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Show Snackbar
    fun snackbar(
        scope: CoroutineScope,
        sbHostState: SnackbarHostState,
        message: String
    ) {
        scope.launch {
            sbHostState.showSnackbar(message)
        }
    }

    // Show AlertDialog
    fun alert(
        title: String,
        message: String,
        positiveButton: String = "OK"
    ) {
        AlertDialog.Builder(currentActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveButton, null)
            show()
        }
    }
}