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


class SignInActivity : ComponentActivity() {
    lateinit var authViewModel: MutableStateFlow<AuthViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        authViewModel = MutableStateFlow(AuthViewModel(email="isaac@gmail.com", password = "password"))
        setContent {
            SignInPage(authViewModel)
        }
    }
}

@Composable
fun SignInPage(authViewModel: MutableStateFlow<AuthViewModel>) {
    val context = LocalContext.current
    val authViewModelState by authViewModel.collectAsState()
    TemplatePage {
//        val listFields = mutableListOf(
//            "Email",
//            "Password",
//            "Confirm Password",
//            "City Code",
//            "City",
//            "Phone Number"
//        )
        Column(modifier = Modifier.padding(30.dp).padding(top = 180.dp).fillMaxSize()) {
            WrapperPadding {
                EniTextField(hintText = "email",
                    value=authViewModelState.email,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(email = value)
                    }
                )
            }
            WrapperPadding {
                EniTextField(hintText = "pseudo",
                    value=authViewModelState.pseudo,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(pseudo = value)
                    }
                )
            }
            WrapperPadding {
                EniTextField(hintText = "password",
                    value=authViewModelState.password,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(password = value)
                    }
                )
            }
            WrapperPadding {
                EniTextField(hintText = "confirm password",
                    value=authViewModelState.passwordConfirm,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(passwordConfirm = value)
                    }
                )
            }
            WrapperPadding {
                EniTextField(hintText = "City Code",
                    value=authViewModelState.cityCode,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(cityCode = value)
                    }
                )
            }
            WrapperPadding {
                EniTextField(hintText = "City",
                    value=authViewModelState.city,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(city = value)
                    }
                )
            }
            WrapperPadding {
                EniTextField(hintText = "Phone Number",
                    value=authViewModelState.phone,
                    onValueChange = {
                            value -> authViewModel.value = authViewModel.value.copy(phone = value)
                    }
                )
            }
            EniSimpleButton(buttonText = "Sign In!") {
                authViewModelState.signIn(context)
            }
//            EniLinkButton(buttonText = "Sign In!",context, LoginActivity::class)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SigninPreview() {
    val authViewModel = MutableStateFlow(AuthViewModel(email = "isaac@gmail.com", password = "password"))
    SignInPage(authViewModel)
}