package com.example.demoandroid.auth

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoandroid.R
import com.example.demoandroid.article.ArticleActivity
import com.example.demoandroid.common.AppAlertHelpers
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.ui.theme.EniTextField
import com.example.demoandroid.ui.theme.EniLinkButton
import com.example.demoandroid.ui.theme.EniSimpleButton
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage
import com.example.demoandroid.ui.theme.WrapperPadding
import kotlinx.coroutines.flow.MutableStateFlow


class LoginActivity : ComponentActivity() {
    lateinit var authViewModel: MutableStateFlow<AuthViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        authViewModel = MutableStateFlow(AuthViewModel(application,email="isaac@gmail.com", password = "password"))

        setContent {
            LoginPage(authViewModel)
        }
    }
}

@Composable
fun LoginPage(authViewModel: MutableStateFlow<AuthViewModel>) {

        val authViewModelState by authViewModel.collectAsState()

    TemplatePage {
        val context = LocalContext.current

        Column(modifier = Modifier.padding(20.dp).fillMaxWidth()) {
            Spacer(Modifier.height(50.dp))
            Image(
                painter = painterResource(R.drawable.logo_eni_round),
                contentDescription = "logo"
            )
            Spacer(Modifier.height(100.dp))
            TitlePage("Login")
            Spacer(Modifier.height(20.dp))

            WrapperPadding {


                EniTextField(hintText = stringResource(R.string.field_email_hint),
                    value=authViewModelState.email,
                    onValueChange = {
                        value -> authViewModel.value = authViewModel.value.copy(email = value)
                    }
                     )
            }
            WrapperPadding {
                EniTextField(hintText = stringResource(R.string.field_password_hint),
                    value=authViewModelState.password,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(password = value)
                    } )
            }
            EniSimpleButton(buttonText = stringResource(R.string.btn_login)) {
                authViewModelState.login(onLoginSuccess = {
                    AppContextHelper.openActivity(context, ArticleActivity::class)
                })
            }
//            EniLinkButton(buttonText="Connexion",context, ArticleActivity::class)
            EniLinkButton(buttonText=stringResource(R.string.btn_link_signIn),context, SignInActivity::class)
            EniLinkButton(buttonText=stringResource(R.string.btn_link_recoverPassword),context, ResetPasswordActivity::class)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val authViewModel = MutableStateFlow(AuthViewModel(Application(), email = "isaac@gmail.com", password = "password"))

    LoginPage(authViewModel)
}