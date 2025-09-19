package com.example.demoandroid.common

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel

open class ENIViewModel(application: Application): AndroidViewModel(application) {

    fun getString(@StringRes resId: Int) : String {
        return this.getApplication<Application>().getString(resId);
    }

}