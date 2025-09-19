package com.example.demoandroid.article

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.R
import com.example.demoandroid.common.AppContextHelper.Companion.commonApiCall
import com.example.demoandroid.common.ENIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleListener(var onRequestView : (article: Article)-> Unit,
                      var onRequestEdit : (article: Article)-> Unit,
                      var onRequestDelete : (article: Article)-> Unit,) {
}
class ArticleViewModel(application: Application): ENIViewModel(application) {
    var isLoading = MutableStateFlow<Boolean>(false)

    var articleListener: ArticleListener? = null;

    var article = MutableStateFlow<Article>(Article("","","","",""))

    var listeArticle = MutableStateFlow<List<Article>>(mutableListOf<Article>(
 ))

    fun getArticlesFromApi(){
        commonApiCall<List<Article>>(getString(R.string.api_articles),viewModelScope,doAction={
            val response = ArticleService.ArticleServiceApi.articleService.getArticles()
            listeArticle.value= response.data!!
            response;
        },
            onSuccess = {
            }
        )
    }

    fun getArticleByIdFromApi(id: String){
        commonApiCall<Article>("Chargement de l'article",viewModelScope,doAction={
            val response = ArticleService.ArticleServiceApi.articleService.getArticleById(id)
            article.value = response.data!!
            response;
        })
    }

    fun saveArticle(onSaveSuccess: () -> Unit= {}){
        commonApiCall<Article>("Enregistrement de l'article",viewModelScope,doAction={
            val response = ArticleService.ArticleServiceApi.articleService.saveArticle(article.value)
            response;},
            onSuccess = onSaveSuccess

        )
    }

    fun deleteArticle(id: String, onDeleteSuccess: () -> Unit={}){
        commonApiCall<Article>("Suppression de l'article",viewModelScope,doAction={
            val response = ArticleService.ArticleServiceApi.articleService.deleteArticleById(id);
            response;},
            onSuccess = onDeleteSuccess
            )
        }
    }

