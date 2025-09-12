package com.example.demoandroid.common

import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

class AppContextHelper {


    companion object {
        fun openActivity(context: Context, activityClass: KClass<*>) {
            val intent = Intent(context,activityClass.java)
            context.startActivity(intent)
        }

        fun openActivityWithExtra(context: Context, activityClass: KClass<*>, extra: String) {
            val intent = Intent(context,activityClass.java)
            intent.putExtra("id",extra)
            context.startActivity(intent)
        }
    }


}