package com.example.demoandroid.ui.theme
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.demoandroid.R
import com.example.demoandroid.article.Article
import com.example.demoandroid.article.ArticleActivity
import com.example.demoandroid.article.ArticleListener
import com.example.demoandroid.article.ArticleViewModel
import com.example.demoandroid.common.AlertDialog
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.common.ProgressDialog

import kotlin.reflect.KClass

@Composable
fun BackgroundImage(@DrawableRes backgroundId: Int = R.drawable.mobile_bg_01_1) {
    Image(
        painter= painterResource(backgroundId),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Box(modifier = Modifier.fillMaxSize().background(color=LightWhite))
}
@Composable
fun TemplatePage(@DrawableRes backgroundId: Int = R.drawable.mobile_bg_01_1,content: @Composable () -> Unit) {
    TpAndroidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                BackgroundImage(backgroundId)
                content()
                ProgressDialog()
                AlertDialog()
            }
        }
    }
}

@Composable
fun WrapperPadding(content: @Composable () -> Unit){
    Box(modifier=Modifier.padding(vertical=5.dp, horizontal=30.dp)){
        content()
    }
}

@Composable
fun TitlePage(text: String = "Titre") {

        Text(text,
            textAlign = TextAlign.Center,
            color = Color(0xFF134a80),
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color(0x770b58d8),
                    offset = Offset(0f, 0f), blurRadius = 5f
                )
            ),
            modifier = Modifier.fillMaxWidth()
        )

}
// Composants de Formulaire
@Composable
fun EniTextField(hintText: String = "Veuillez saisir ...", value: String, onValueChange: (String)-> Unit = {} ) {
    TextField(value = value, onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(focusedContainerColor = Color.White,
            unfocusedContainerColor = LightBlue,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Blue ),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(hintText)
        })
}

@Composable
fun EniLinkButton(buttonText: String, context: Context, activityClass: KClass<*>  ){
    Button(onClick = {
        AppContextHelper.openActivity(context,activityClass)
    }
        , modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent
    )){
        Box(modifier = Modifier.fillMaxWidth()
                                .background(brush = RedOrangegradient,shape=RoundedCornerShape(10.dp)).border(width=2.dp,color=Red80,RoundedCornerShape(10.dp))
                                .padding(10.dp),contentAlignment = Alignment.Center ){
            Text(text=buttonText)
        }
    }
}

@Composable
fun EniSimpleButton(buttonText: String, onclick: () -> Unit ){
    Button(onClick = {onclick()}
        , modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )){
        Box(modifier = Modifier.fillMaxWidth()
            .background(brush = RedOrangegradient,shape=RoundedCornerShape(10.dp)).border(width=2.dp,color=Red80,RoundedCornerShape(10.dp))
            .padding(10.dp),contentAlignment = Alignment.Center ){
            Text(text=buttonText)
        }
    }
}

@Composable
fun ArticleCard(article: Article, articleListener: ArticleListener){
    val context = LocalContext.current
    Box(modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)){
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row {
                        AsyncImage(
                            placeholder = painterResource(R.drawable.article_placeholder),
                            model = article.imgPath,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(modifier = Modifier.padding(10.dp),text=article.title, fontWeight = FontWeight.Bold)
                            Text(modifier = Modifier.padding(10.dp),text=article.desc)
                            EniSimpleButton("Détail", {
                                articleListener.onRequestView(article)
                            })
                            EniSimpleButton("Modifier", {
                                articleListener.onRequestEdit(article)
                            })
                            EniSimpleButton("Supprimer", {
                                articleListener.onRequestDelete(article)
                            })
                        }
                        }
                    }
                    Box( modifier = Modifier.background( brush = Brush.linearGradient(
                                listOf(Color(0xFF0b58d8), Color(0xFF31a9ff))
                            )
                        )
                        .fillMaxWidth()
                        .height(4.dp))

                }
            }
    }



@Composable
fun ArticleCardDetail(article: Article, onRequestDelete: (id: String) -> Unit = {} , articleListener: ArticleListener){
    val context = LocalContext.current
    Box(modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)){
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                    AsyncImage(
                        //Le placeholder peux écraser le texte. A corriger
                        placeholder = painterResource(R.drawable.article_placeholder),
                        model = article.imgPath,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(modifier = Modifier.padding(10.dp),text=article.title, fontWeight = FontWeight.Bold)
                        Text(modifier = Modifier.padding(10.dp),text=article.desc)
                        EniSimpleButton("Retour", {
                            AppContextHelper.openActivity(context, ArticleActivity::class)
                        })
                        EniSimpleButton("Supprimer",

                            {
                                articleListener.onRequestDelete(article)
                            }

                        )
                    }
                Box( modifier = Modifier.background( brush = Brush.linearGradient(
                    listOf(Color(0xFF0b58d8), Color(0xFF31a9ff))
                )
                )
                    .fillMaxWidth()
                    .height(4.dp))

            }
        }
    }
}