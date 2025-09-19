package com.example.demoandroid.article

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument



class ArticleActivity : ComponentActivity() {

    lateinit var articleViewModel: ArticleViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        articleViewModel = ArticleViewModel(application);


        articleViewModel.getArticlesFromApi();
        setContent {
            ArticlePage(articleViewModel)
        }

    }
}

@Composable
fun ArticlePage(articleViewModel: ArticleViewModel){
    val navController = rememberNavController();

    articleViewModel.articleListener = ArticleListener(
        onRequestView= {article-> navController.navigate("detail/${article.id!!}")},
        onRequestEdit = {article-> navController.navigate("edit")},
        onRequestDelete ={article-> articleViewModel.deleteArticle(article.id!!) },
    )

    NavHost(
        navController=navController,
        startDestination = "list")
    {
        composable("list"){ ListArticlePage(articleViewModel)}
        composable("detail/{id}", arguments = listOf(navArgument("id"){ type = NavType.StringType})){
            backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            ArticleDetailPage(articleViewModel)
        }
        composable("edit"){ ArticleEditPage(articleViewModel)}
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    ArticlePage(ArticleViewModel(Application()))
}