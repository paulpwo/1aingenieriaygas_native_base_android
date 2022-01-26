package com.pwol.flutter_app_1agas2.data

import android.R
import android.R.attr
import android.content.Context
import com.pwol.flutter_app_1agas2.data.model.LoggedInUser
import com.pwol.flutter_app_1agas2.service.ApiInterface
import com.pwol.flutter_app_1agas2.service.model.User
import com.pwol.flutter_app_1agas2.service.model.UserLogin
import com.pwol.flutter_app_1agas2.ui.login.LoginActivity
import retrofit2.Call
import java.io.IOException
import retrofit2.Callback
import retrofit2.Response


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    val KEY_USERNAME = "username"
    val KEY_PASSWORD = "password"
    fun login(username: String, password: String, context: Context): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication

            val editor = context
                ?.getSharedPreferences("pref", Context.MODE_PRIVATE)
                ?.edit()
            editor?.putBoolean("isLogin", true)
            editor?.putString("token", "")
            editor?.putString("email", "")
            editor?.putString("name", "")
            editor?.commit()


            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe", "email", "asdfasf", "asdf")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout(context: Context) {
        // TODO: revoke authentication

        val editor = context
            ?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            ?.edit()
        editor?.putBoolean("isLogin", false)
        editor?.putString("token", "")
        editor?.putString("email", "")
        editor?.putString("name", "")
        editor?.commit()
    }
}