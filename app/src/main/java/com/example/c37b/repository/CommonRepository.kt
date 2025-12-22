package com.example.c37b.repository

import android.content.Context
import android.net.Uri

interface CommonRepository {

    fun uploadImage(
        context: Context,
        imageUri: Uri,
        callback: (String?) -> Unit
    )

    fun getFileNameFromUri(context: Context,
                           imageUri: Uri): String?
}