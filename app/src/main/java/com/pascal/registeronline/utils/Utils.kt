package com.pascal.registeronline.utils

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.core.content.ContextCompat
import com.pascal.registeronline.utils.Constant.FORMAT_DATE
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun getCurrentFormattedDate(): String {
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun intentActionView(context: Context, url: String) {
    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    ContextCompat.startActivity(context, i, null)
}

@Throws(IOException::class)
private fun Uri.getFileExtension(contentResolver: ContentResolver): String {
    var extension: String? = null

    if (ContentResolver.SCHEME_CONTENT == scheme && contentResolver.getType(this)?.startsWith("image/") == true) {
        val cursor: Cursor? = contentResolver.query(this, arrayOf(MediaStore.Images.ImageColumns.MIME_TYPE), null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(MediaStore.Images.ImageColumns.MIME_TYPE)
                if (columnIndex != -1) {
                    val mimeType = it.getString(columnIndex)
                    extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
                }
            }
        }
    }

    if (extension.isNullOrEmpty()) {
        extension = MimeTypeMap.getFileExtensionFromUrl(toString())
    }

    if (extension.isNullOrEmpty()) {
        extension = "jpg"
    }

    return extension!!
}

fun checkInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            return true
        }
    }
    return false
}

fun setMandatoryTitle(option: String?, isMandatory: Boolean = true): AnnotatedString {
    val safeOption = option.orEmpty()

    return buildAnnotatedString {
        append(safeOption)
        if (isMandatory) {
            withStyle(style = SpanStyle(color = Red)) {
                append("*")
            }
        }
    }
}

fun String.maskNumber(
    visibleStart: Int = 2,
    visibleEnd: Int = 2,
    maskChar: Char = '*'
): String {
    if (length <= visibleStart + visibleEnd) return this

    val start = take(visibleStart)
    val end = takeLast(visibleEnd)
    val masked = maskChar.toString().repeat(length - visibleStart - visibleEnd)

    return start + masked + end
}