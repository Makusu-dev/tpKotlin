package com.example.demoandroid.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoandroid.article.ArticleActivity
import com.example.demoandroid.ui.theme.EniTextField
import com.example.demoandroid.ui.theme.EniLinkButton
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.WrapperPadding


class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignInPage()
        }
    }
}

@Composable
fun SignInPage() {
    val context = LocalContext.current;
    TemplatePage {
        val listFields = mutableListOf(
            "Pseudo",
            "Email",
            "Password",
            "Confirm Password",
            "City Code",
            "City",
            "Phone Number"
        )
        Column(modifier = Modifier.padding(30.dp).padding(top = 180.dp).fillMaxSize()) {
            for (el in listFields) {
                WrapperPadding {
                    EniTextField(hintText = el)
                }
            }
            EniLinkButton(buttonText = "Sign In!",context, ArticleActivity::class)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SigninPreview() {
    SignInPage()
}