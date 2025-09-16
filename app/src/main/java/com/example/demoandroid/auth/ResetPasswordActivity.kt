package com.example.demoandroid.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoandroid.ui.theme.EniTextField
import com.example.demoandroid.ui.theme.EniSimpleButton
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.WrapperPadding
import kotlinx.coroutines.flow.MutableStateFlow


class ResetPasswordActivity : ComponentActivity() {
    lateinit var authViewModel: MutableStateFlow<AuthViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        authViewModel = MutableStateFlow(AuthViewModel())

        setContent {
            PasswordRecoverPage(authViewModel)
        }
    }
}


@Composable
fun PasswordRecoverPage(authViewModel: MutableStateFlow<AuthViewModel>) {
    val context = LocalContext.current
    val authViewModelState by authViewModel.collectAsState()
    TemplatePage {
        Column(modifier = Modifier.padding(30.dp).padding(top = 180.dp).fillMaxSize()) {
            WrapperPadding {
                EniTextField(hintText = "email",
                    value=authViewModelState.email,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(email = value)
                    }
                )
            }
            EniSimpleButton(buttonText = "Get new password") {
                authViewModelState.recoverPassword(context)
            }
//            EniLinkButton(buttonText = "Envoyer le lien de récupération",context, ArticleActivity::class)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPreview() {
    val authViewModel = MutableStateFlow(AuthViewModel())
    PasswordRecoverPage(authViewModel)
}