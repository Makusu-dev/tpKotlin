package com.example.demoandroid.article

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.ui.theme.ArticleCard
import com.example.demoandroid.ui.theme.ArticleCardDetail
import com.example.demoandroid.ui.theme.EniSimpleButton
import com.example.demoandroid.ui.theme.EniTextField
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage
import com.example.demoandroid.ui.theme.WrapperPadding


class ArticleEditActivity : ComponentActivity() {
    lateinit var articleViewModel: ArticleViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        articleViewModel = ArticleViewModel(application);
        var articleId: String? = intent.getStringExtra("id")
        if(articleId!=null){
            articleViewModel.getArticleByIdFromApi(articleId);
        }

        setContent {
            ArticleEditPage(articleViewModel)
        }
    }
}

@Composable
fun ArticleEditPage(articleViewModel: ArticleViewModel){
    // TemplatePage a un paramètre de type lambda-> unit on peux l'écrire directement entre accolades
    TemplatePage {
        val articleState by articleViewModel.article.collectAsState()
        val context= LocalContext.current;

        Column{
            Spacer(Modifier.height(250.dp))
            TitlePage("Ajouter/modifier un article")
            Spacer(Modifier.height(20.dp))
            Column {
                WrapperPadding {
                    EniTextField(hintText = "Title",
                        value=articleState.title,
                        onValueChange = {
                                value -> articleViewModel.article.value = articleViewModel.article.value.copy(title = value)
                        }
                    )
                }
                WrapperPadding {
                    EniTextField(hintText = "Description",
                        value=articleState.desc,
                        onValueChange = {
                                value -> articleViewModel.article.value = articleViewModel.article.value.copy(desc = value)
                        }
                    )
                }
                WrapperPadding {
                    EniTextField(hintText = "Auteur",
                        value=articleState.author,
                        onValueChange = {
                                value -> articleViewModel.article.value = articleViewModel.article.value.copy(author = value)
                        }
                    )
                }
//                WrapperPadding {
//                    EniTextField(hintText = "image",
//                        value=articleState.desc,
//                        onValueChange = {
//                                value -> articleViewModel.article.value = articleViewModel.article.value.copy(desc = value)
//                        }
//                    )
//                }
            }
            EniSimpleButton("Enregistrer", {
                articleViewModel.saveArticle(onSaveSuccess = {
                    AppContextHelper.openActivity(context, ArticleActivity::class)
                })
            })
//            if(articleState.id!=null) {
//                EniSimpleButton("Supprimer", {
//                    articleViewModel.deleteArticle(context,articleState.id!!)
//                })
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleEditPreview() {
    ArticleEditPage(ArticleViewModel(Application()))
}