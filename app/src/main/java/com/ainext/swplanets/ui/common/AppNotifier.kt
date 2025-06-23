package com.ainext.swplanets.ui.common

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import com.ainext.swplanets.SwPlanetsApp.Companion.context
import com.ainext.swplanets.SwPlanetsApp.Companion.mActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppNotifier {

    // Show Toast
    fun toast(message: String, duration: ToastDuration = ToastDuration.SHORT) {
        Toast.makeText(context, message, duration.value).show()
    }

    // Show SnackBar
    fun snackBar(
        scope: CoroutineScope,
        sbHostState: SnackbarHostState,
        message: String
    ) {
        scope.launch {
            sbHostState.showSnackbar(message)
        }
    }

    // Show AlertDialog
    fun alert(config: AlertConfig) {

        if (mActivity?.isFinishing == false) {
            val builder = AlertDialog.Builder(mActivity)
                .setTitle(config.title)
                .setMessage(config.message)
                .setPositiveButton(config.positiveButtonText) { _, _ ->
                    config.onPositiveClick?.invoke()
                }

            // Add negative button if provided
            config.negativeButtonText?.let {
                builder.setNegativeButton(it) { _, _ ->
                    config.onNegativeClick?.invoke()
                }
            }

            builder.show()
        }
    }
}