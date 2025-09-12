package com.example.demoandroid.common

import android.app.ProgressDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.room.util.copy
import kotlinx.coroutines.flow.MutableStateFlow

class AppProgressHelper {

    companion object {
        val instance : AppProgressHelper by lazy { AppProgressHelper() }

        fun get() : AppProgressHelper {
            return instance;
        }
    }

    // Sert à savoir en temps réel si il faut afficher ou pas la dialog
    var alertDialogModelData = MutableStateFlow(AlertDialogModelData());

    fun show(message: String){
        alertDialogModelData.value=alertDialogModelData.value.copy(isShow= true, message = message);

    }

    fun close(){
        alertDialogModelData.value=alertDialogModelData.value.copy(isShow= false);
    }

}

@Composable
fun ProgressDialog(){
    val modelData by AppProgressHelper.get().alertDialogModelData.collectAsState()

    if (modelData.isShow){
        Dialog(onDismissRequest = {}){
            Box(modifier = Modifier.background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(30.dp)
            )
                .padding(20.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Text(text = modelData.message)
                }
            }
        }
    }

}