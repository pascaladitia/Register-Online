package com.pascal.registeronline.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PreferencesLogin {

    private const val PREFS_NAME = "login_prefs"
    private const val IS_SAVE_LOGIN = "is_save_login"
    private const val ACCESS_TOKEN = "access_token"
    private const val EMAIL = "email"
    private const val PASSWORD = "password"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private inline fun edit(context: Context, block: SharedPreferences.Editor.() -> Unit) {
        prefs(context).edit(commit = true, block)
    }

    fun saveIsLogin(context: Context, isSaveLogin: Boolean) =
        edit(context) { putBoolean(IS_SAVE_LOGIN, isSaveLogin) }

    fun saveAccessToken(context: Context, accessToken: String) =
        edit(context) { putString(ACCESS_TOKEN, accessToken) }

    fun saveEmail(context: Context, email: String) =
        edit(context) { putString(EMAIL, email) }

    fun savePassword(context: Context, password: String) =
        edit(context) { putString(PASSWORD, password) }

    fun getAccessToken(context: Context): String =
        prefs(context).getString(ACCESS_TOKEN, "") ?: ""

    fun getIsLogin(context: Context): Boolean =
        prefs(context).getBoolean(IS_SAVE_LOGIN, false)

    fun getEmail(context: Context): String =
        prefs(context).getString(EMAIL, "") ?: ""

    fun getPassword(context: Context): String =
        prefs(context).getString(PASSWORD, "") ?: ""

    fun clear(context: Context) =
        edit(context) { clear() }
}
