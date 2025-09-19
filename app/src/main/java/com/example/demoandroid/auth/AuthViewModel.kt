package com.example.demoandroid.auth


import androidx.lifecycle.viewModelScope
import com.example.demoandroid.common.AppAlertHelpers
import com.example.demoandroid.common.AppContextHelper.Companion.commonApiCall
import com.example.demoandroid.common.AppProgressHelper
import com.example.demoandroid.common.ENIViewModel
import kotlinx.coroutines.launch
import android.app.Application


data class AuthViewModel(val _application: Application,var email: String="",var pseudo: String="", var password: String="",var passwordConfirm: String="", var cityCode: String="", var city: String="", var phone: String="" ): ENIViewModel(_application) {

    //Ne semble pas fonctionner
//    var userToLogin = MutableStateFlow<UserModelData>(UserModelData("isaac@gmail.com","password"));


    fun login(onLoginSuccess: () -> Unit= {}) {
        commonApiCall<String>("Connexion en cours",viewModelScope,doAction={
            val userLogin= UserModelData(email,password)
            val response = AuthService.AuthServiceApi.authService.login(userLogin)
            if(response.code=="200"){
                AuthContext.Companion.get().setAuthToken(response.data!!)
            };
            response;}, onSuccess = onLoginSuccess
        )
    }

    fun signIn(onSigninSuccess: () -> Unit={}) {
        commonApiCall<SignUpResponse>("Connexion en cours",viewModelScope,doAction={
            val userToSignIn = SignupRequest(email=email, pseudo = pseudo,password = password, passwordConfirm=passwordConfirm,cityCode=cityCode,city=city,phone= phone)
            val response = AuthService.AuthServiceApi.authService.signup(userToSignIn)
            response;
            },
            onSuccess = onSigninSuccess
        )
    }

    //Non refactorisé pour le moment car le comportement après succès est plus complexe
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