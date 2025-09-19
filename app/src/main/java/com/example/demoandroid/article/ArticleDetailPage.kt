package com.example.demoandroid.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.demoandroid.common.AppContextHelper
import com.example.demoandroid.ui.theme.ArticleCardDetail
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage

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
            }, articleViewModel.articleListener!!)
        }
    }
}