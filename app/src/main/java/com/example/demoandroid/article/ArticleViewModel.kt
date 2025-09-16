package com.example.demoandroid.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.common.AppAlertHelpers
import com.example.demoandroid.common.AppProgressHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ArticleViewModel: ViewModel() {
    var isLoading = MutableStateFlow<Boolean>(false)

    var article = MutableStateFlow<Article>(Article("Id","Titre","Description","Auteur","imageDePetitChien"))

    var listeArticle = MutableStateFlow<List<Article>>(mutableListOf<Article>(
//        Article("Switch 2", "Console portable", "https://m.media-amazon.com/images/I/717JrHodikL.jpg"),
//        Article("Kebab", "nourriture moyennement saine mais accessible et satisfaisante", "https://ffcuisine.fr/wp-content/uploads/2024/11/115370_recette-facile-dassiette-kebab-maison-savourez-le-gout-authentique.jpg"),
    ))

    fun addArticle(id: String="1",title: String ="Nouvel article",description: String = "Description du nouvel article",author: String="Auteur de l'article", imagePath: String = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fstatic.vecteezy.com%2Fsystem%2Fresources%2Fpreviews%2F016%2F916%2F479%2Fnon_2x%2Fplaceholder-icon-design-free-vector.jpg&f=1&nofb=1&ipt=a3b814fd70f1b5fd2a9d4a4f6320b421509a7f28c3f5c0b00d906b091af034aa") {
        listeArticle.value = listeArticle.value + Article("15444452",title,description,author,imagePath)
    }


    fun getArticlesFromApi(){
        // Affiche un ecran de chargement
        AppProgressHelper.get().show("Chargement des articles")
        viewModelScope.launch {
            // Fake loading duration
            delay(duration = 2.seconds)
            val response = ArticleService.ArticleServiceApi.articleService.getArticles()
            listeArticle.value= response.data!!
            //fermer un écran de chargement a la fin de l'appel async
            AppProgressHelper.get().close()

            //afficher le message du back
            AppAlertHelpers.get().show(response.message)
        }

    }

    fun getArticleByIdFromApi(id: String){

        // Affiche un ecran de chargement

        viewModelScope.launch {
            val response = ArticleService.ArticleServiceApi.articleService.getArticleById(id)
            article.value = response.data!!

        //fermer un écran de chargement a la fin de l'appel async

        }
    }

}