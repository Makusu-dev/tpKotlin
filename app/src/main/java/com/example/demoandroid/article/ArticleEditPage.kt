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
import com.example.demoandroid.ui.theme.EniSimpleButton
import com.example.demoandroid.ui.theme.EniTextField
import com.example.demoandroid.ui.theme.TemplatePage
import com.example.demoandroid.ui.theme.TitlePage
import com.example.demoandroid.ui.theme.WrapperPadding

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