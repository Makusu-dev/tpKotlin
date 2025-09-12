package com.example.demoandroid.auth

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoandroid.R
import com.example.demoandroid.article.ArticleActivity
import com.example.demoandroid.ui.theme.EniTextField
import com.example.demoandroid.ui.theme.EniLinkButton
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage
import com.example.demoandroid.ui.theme.WrapperPadding


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginPage()
        }
    }
}

@Composable
fun LoginPage() {
    TemplatePage {
        val listFields = mutableListOf(
            "Email",
            "Password"
        )

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
            for (el in listFields) {
                WrapperPadding {
                    EniTextField(hintText = el)
                }
            }
            EniLinkButton(buttonText="Connexion",context, ArticleActivity::class)
            EniLinkButton(buttonText="Inscription",context, SignInActivity::class)
            EniLinkButton(buttonText="Mot de passe oublié",context, ResetPasswordActivity::class)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginPage()
}