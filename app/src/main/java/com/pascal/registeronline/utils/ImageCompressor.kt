package com.pascal.registeronline.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

object ImageCompressor {

    fun compressToMax2MB(filePath: String): File {
        val file = File(filePath)
        if (!file.exists()) return file

        var bitmap = BitmapFactory.decodeFile(file.absolutePath)
        var quality = 100

        val compressedFile = File(file.parent, "compress_${file.name}")

        do {
            val stream = FileOutputStream(compressedFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
            stream.flush()
            stream.close()

            quality -= 5
        } while (compressedFile.length() > 2_000_000 && quality > 10)

        return compressedFile
    }
}