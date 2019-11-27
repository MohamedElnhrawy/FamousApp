package com.example.famousapp.famous.utils.common

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.graphics.BitmapFactory
import androidx.constraintlayout.widget.Constraints.TAG
import java.io.*
import java.nio.file.Files.delete
import android.provider.MediaStore
import android.content.ContentResolver
import android.content.ContentValues
import androidx.annotation.NonNull
import android.provider.MediaStore.Images.Thumbnails.IMAGE_ID
import android.content.ContentUris
import android.graphics.Matrix
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import android.provider.MediaStore.Images;


object ImageUtils {
    fun getImageName(path : String) : String {
        return  path.replace("/","")
            .replace(" ","").trim()
    }

    @Throws(IOException::class)
     fun saveBitmap(
        @NonNull context: Context, @NonNull bitmap: Bitmap,
        @NonNull displayName: String
    ) {
        val relativeLocation = Environment.DIRECTORY_PICTURES

        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation)

        val resolver = context.contentResolver

        var stream: OutputStream? = null
        var uri: Uri? = null

        try {
            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            uri = resolver.insert(contentUri, contentValues)

            if (uri == null) {
                throw IOException("Failed to create new MediaStore record.")
            }

            stream = resolver.openOutputStream(uri)

            if (stream == null) {
                throw IOException("Failed to get output stream.")
            }

            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream) == false) {
                throw IOException("Failed to save bitmap.")
            }
        } catch (e: IOException) {
            if (uri != null) {
                resolver.delete(uri, null, null)
            }

            throw e
        } finally {
            stream?.close()
        }
    }

    fun insertImage(
        cr: ContentResolver,
        source: Bitmap?,
        title: String
    ): String? {

        val values = ContentValues()
        values.put(Images.Media.TITLE, title)
        values.put(Images.Media.DISPLAY_NAME, title)
       // values.put(Images.Media.DESCRIPTION, description)
        values.put(Images.Media.MIME_TYPE, "image/jpeg")
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis())

        var url: Uri? = null
        var stringUrl: String? = null    /* value to be returned */

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            if (source != null) {
                val imageOut = cr.openOutputStream(url!!)
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut)
                } finally {
                    imageOut!!.close()
                }

                val id = ContentUris.parseId(url)
                // Wait until MINI_KIND thumbnail is generated.
                val miniThumb =
                    MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null)
                // This is for backward compatibility.
                storeThumbnail(cr, miniThumb, id, 50f, 50f, MediaStore.Images.Thumbnails.MICRO_KIND)
            } else {
                cr.delete(url!!, null, null)
                url = null
            }
        } catch (e: Exception) {
            if (url != null) {
                cr.delete(url, null, null)
                url = null
            }
        }

        if (url != null) {
            stringUrl = url.toString()
        }

        return stringUrl
    }

    /**
     * A copy of the Android internals StoreThumbnail method, it used with the insertImage to
     * populate the android.provider.MediaStore.Images.Media#insertImage with all the correct
     * meta data. The StoreThumbnail method is private so it must be duplicated here.
     * @see android.provider.MediaStore.Images.Media
     */
    private fun storeThumbnail(
        cr: ContentResolver,
        source: Bitmap,
        id: Long,
        width: Float,
        height: Float,
        kind: Int
    ): Bitmap? {

        // create the matrix to scale it
        val matrix = Matrix()

        val scaleX = width / source.width
        val scaleY = height / source.height

        matrix.setScale(scaleX, scaleY)

        val thumb = Bitmap.createBitmap(
            source, 0, 0,
            source.width,
            source.height, matrix,
            true
        )

        val values = ContentValues(4)
        values.put(Images.Thumbnails.KIND, kind)
        values.put(Images.Thumbnails.IMAGE_ID, id.toInt())
        values.put(Images.Thumbnails.HEIGHT, thumb.height)
        values.put(Images.Thumbnails.WIDTH, thumb.width)

        val url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values)

        try {
            val thumbOut = cr.openOutputStream(url!!)
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut)
            thumbOut!!.close()
            return thumb
        } catch (ex: FileNotFoundException) {
            return null
        } catch (ex: IOException) {
            return null
        }

    }
}
