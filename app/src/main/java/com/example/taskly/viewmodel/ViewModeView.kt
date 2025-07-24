package com.example.taskly.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import java.io.File
import javax.inject.Singleton

@Singleton
class ViewModelView : ViewModel(){
    fun saveBitMapToCatch(context: Context, bitmap: Bitmap):Uri{
        val file: File = File(context.cacheDir, "Taskly_profile1_image.jpg")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }

        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }
}