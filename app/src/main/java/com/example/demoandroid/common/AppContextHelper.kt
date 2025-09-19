package com.example.demoandroid.common

import android.content.Context
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.api.ApiResponse
import com.example.demoandroid.article.ArticleService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class AppContextHelper {


    companion object {
        fun openActivity(context: Context, activityClass: KClass<*>) {
            val intent = Intent(context,activityClass.java)
            context.startActivity(intent)
        }

        fun openActivityWithExtra(context: Context, activityClass: KClass<*>, extra: String) {
            val intent = Intent(context,activityClass.java)
            intent.putExtra("id",extra)
            context.startActivity(intent)
        }

        fun <T> commonApiCall(loadingMsg : String = "chargement", coroutineScope: CoroutineScope, doAction: suspend () -> ApiResponse<T>, onSuccess: (ApiResponse<T>) -> Unit= {} ){

            // Affiche un ecran de chargement avant un appel async
            AppProgressHelper.get().show(loadingMsg)

            coroutineScope.launch {
                val response = doAction()

                //fermer un écran de chargement a la fin de l'appel async
                AppProgressHelper.get().close()

                //On affiche le message du back
                AppAlertHelpers.Companion.get().show(response.message, onClose = {
                    if (response.code == "200") {
                        {onSuccess(response)}
                    }
                })

            }
        }
    }


}