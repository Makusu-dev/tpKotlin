package com.example.demoandroid.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.article.ArticleActivity
import com.example.demoandroid.common.AppAlertHelpers
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.common.AppProgressHelper
import kotlinx.coroutines.launch


data class AuthViewModel(var email: String="",var pseudo: String="", var password: String="",var passwordConfirm: String="", var cityCode: String="", var city: String="", var phone: String="" ): ViewModel() {

    //Ne semble pas fonctionner
//    var userToLogin = MutableStateFlow<UserModelData>(UserModelData("isaac@gmail.com","password"));


    fun login(onLoginSuccess: () -> Unit= {}) {
        AppProgressHelper.get().show("Connexion en cours")
        viewModelScope.launch {
            //a suprimer lorsque l'on liera aux champs
//            val userToLogin = UserModelData("isaac@gmail.com","password")

            val userLogin= UserModelData(email,password)
            val response = AuthService.AuthServiceApi.authService.login(userLogin)


            //fermer un écran de chargement a la fin de l'appel async
            AppProgressHelper.get().close()

            if(response.code=="200"){
                AuthContext.Companion.get().setAuthToken(response.data!!)
            }

            //afficher le message du back
            AppAlertHelpers.get().show(response.message, onClose = {
                // Si Code success alors ouvrir la page list article
                if (response.code=="200"){
                    onLoginSuccess()
                }
            })


        }
    }

    fun signIn(onSigninSuccess: () -> Unit={}) {

        AppProgressHelper.get().show("Connexion en cours")
        viewModelScope.launch {
            val userToSignIn = SignupRequest(email=email, pseudo = pseudo,password = password, passwordConfirm=passwordConfirm,cityCode=cityCode,city=city,phone= phone)
            val response = AuthService.AuthServiceApi.authService.signup(userToSignIn)

            AppProgressHelper.get().close()

            AppAlertHelpers.get().show(response.message, onClose = {
                if(response.code=="200") {
                    onSigninSuccess()
                }
            })


        }
    }

    fun recoverPassword(onRecoverSuccess: ()-> Unit={}){

        AppProgressHelper.Companion.get().show(message = "chargement")
        viewModelScope.launch {
            val userWithMail = UserModelData(email= email,password="")
            val response = AuthService.AuthServiceApi.authService.resetPassword(userWithMail)
            AppProgressHelper.get().close()

            AppAlertHelpers.Companion.get().show(response.message, onClose={
                if (response.code=="200"){
                    // ATTENTION TEST DE LA FONCTION, MOT DE PASSE AFFICHE EN CLAIR
                    AppAlertHelpers.Companion.get().show("Bonjour "+email+". Votre nouveau mot de passe est: " + response.data, onClose = {
                      onRecoverSuccess
                    })
                }
            })
        }

    }
}