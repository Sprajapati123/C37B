package com.example.c37b.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.c37b.repository.CommonRepository

class CommonViewModel(val repo: CommonRepository) : ViewModel() {

    fun uploadImage(
        context: Context,
        imageUri: Uri,
        callback: (String?) -> Unit
    ){
        repo.uploadImage(context,imageUri,callback)
    }
}