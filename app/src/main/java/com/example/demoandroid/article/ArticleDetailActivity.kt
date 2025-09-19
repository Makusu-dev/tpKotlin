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
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage


class ArticleDetailActivity : ComponentActivity() {
    lateinit var articleViewModel: ArticleViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        articleViewModel = ArticleViewModel(application);
        var articleId = intent.getStringExtra("id")
        articleViewModel.getArticleByIdFromApi(articleId!!);

        setContent {
            ArticleDetailPage(articleViewModel)
        }
    }
}

@Composable
fun ArticleDetailPage(articleViewModel: ArticleViewModel){
    val context = LocalContext.current
    // TemplatePage a un paramètre de type lambda-> unit on peux l'écrire directement entre accolades
    TemplatePage {
        val articleState by articleViewModel.article.collectAsState()

        Column{
            Spacer(Modifier.height(250.dp))
            TitlePage("Détail de l'article")
            Spacer(Modifier.height(20.dp))
            Column {

            }
            ArticleCardDetail(articleState, onRequestDelete = {
                articleViewModel.deleteArticle(articleState.id!!, onDeleteSuccess = {
                    AppContextHelper.openActivity(context, ArticleActivity::class);
                });
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailPreview() {
    ArticleDetailPage(ArticleViewModel(Application()))
}