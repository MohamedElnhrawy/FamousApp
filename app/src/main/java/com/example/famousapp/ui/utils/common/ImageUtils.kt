package com.example.famousapp.famous.utils.common

import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


object ImageUtils {
    fun saveImageToStorage(bitmap: Bitmap, imageName : String){
       // val d = BitmapDrawable(context.getResources(), bitmap)
        val externalStorageState = Environment.getExternalStorageState()
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            val storageDirectory = Environment.getExternalStorageDirectory().toString()
            val  file = File(storageDirectory,imageName)
            try {
            val stream : OutputStream = FileOutputStream(file)
           // var  drawable = ContextCompat.getDrawable(context,d)
            //var bitmap = (drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
                Log.e("saved", Uri.parse(file.absolutePath).toString())
            }catch (e : Exception){
                e.printStackTrace()
            }

        }else{
            Log.e("error","enable to access storage")
        }
    }

    fun getImageName(path : String) : String {
        return  path.replace("/","")
            .replace(" ","").trim()

    }
}
