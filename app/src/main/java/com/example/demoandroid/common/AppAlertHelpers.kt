package com.example.demoandroid.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.flow.MutableStateFlow

class AppAlertHelpers {

    data class AlertModelData(var isShow: Boolean = false, var message :  String = "", var onClose : () -> Unit = {}) {
    }

    companion object {
        val instance : AppAlertHelpers by lazy { AppAlertHelpers() }

        fun get() : AppAlertHelpers {
            return instance
        }
    }

    // Sert à savoir en temps réel si il faut afficher ou pas la dialog
    var alertModelData = MutableStateFlow(AlertModelData())

    fun show(message: String, onClose : () -> Unit = {}){
        alertModelData.value=alertModelData.value.copy(isShow= true, message = message, onClose = onClose)

    }

    fun close(){
        alertModelData.value=alertModelData.value.copy(isShow= false)
        alertModelData.value.onClose()
    }

}

@Composable
fun AlertDialog() {
    val modelData by AppAlertHelpers.get().alertModelData.collectAsState()
    if(modelData.isShow){
        Dialog(onDismissRequest = {
            //on ferme la popup
            AppAlertHelpers.get().close()
        }) {
            Box(
                modifier = Modifier.background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(30.dp)
                )
                    .padding(20.dp)
            )
            {
                Text(text=modelData.message)
            }
        }
    }

}