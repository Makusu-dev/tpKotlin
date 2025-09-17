package com.example.demoandroid.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.common.ProgressDialog
import com.example.demoandroid.ui.theme.ArticleCard
import com.example.demoandroid.ui.theme.EniSimpleButton
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage


class ArticleActivity : ComponentActivity() {

    lateinit var articleViewModel: ArticleViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        articleViewModel = ArticleViewModel();

        articleViewModel.getArticlesFromApi();
        setContent {
            ArticlePage(articleViewModel)
        }

    }
}

@Composable
fun ArticlePage(articleViewModel: ArticleViewModel){
    val context=LocalContext.current;
    //on écoute is Loading
    val isLoadingState by articleViewModel.isLoading.collectAsState()
    // TemplatePage a un paramètre de type lambda-> unit on peux l'écrire directement entre accolades
    TemplatePage {
        Column{
            Spacer(Modifier.height(250.dp))
            TitlePage("Liste d'articles")
            Spacer(Modifier.height(20.dp))
//            EniSimpleButton(buttonText = "Supprimer tout les articles",onclick = {
//                //incrémenter
//                articleViewModel.wipeArticles();
//            })
//            EniSimpleButton(buttonText = "Charger les articles",onclick = {
//                //Recharger la liste des articles
//                articleViewModel.getArticlesFromApi();
//            })
            EniSimpleButton(buttonText = "Ajouter un article",onclick = {
                //incrémenter
//                articleViewModel.addArticle();
                AppContextHelper.openActivity(context, ArticleEditActivity::class)
            })
            ArticleListView(articleViewModel)
        }
    }
}

@Composable
fun ArticleListView(articleViewModel: ArticleViewModel) {
    val articleListState by articleViewModel.listeArticle.collectAsState()

    LazyColumn(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()) {
        items(articleListState) { article ->
            ArticleCard(article,articleViewModel)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    ArticlePage(ArticleViewModel())
}