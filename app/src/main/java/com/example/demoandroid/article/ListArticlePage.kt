package com.example.demoandroid.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.ui.theme.ArticleCard
import com.example.demoandroid.ui.theme.EniSimpleButton
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage

@Composable
fun ListArticlePage(articleViewModel: ArticleViewModel){
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
                articleViewModel.getArticlesFromApi();
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
            ArticleCard(article,articleViewModel.articleListener!!)

        }
    }
}