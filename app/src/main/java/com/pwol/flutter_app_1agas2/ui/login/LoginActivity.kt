package com.pwol.flutter_app_1agas2.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import com.pwol.flutter_app_1agas2.MainActivity
import com.pwol.flutter_app_1agas2.databinding.ActivityLoginBinding

import com.pwol.flutter_app_1agas2.R
import com.pwol.flutter_app_1agas2.service.ApiInterface
import com.pwol.flutter_app_1agas2.service.model.User
import com.pwol.flutter_app_1agas2.service.model.UserLogin
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException





class LoginActivity : AppCompatActivity() {

    private var loading: ProgressBar? = null
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    var contextOfApplicationContext: Context? = null
    private var menuAutocomplete: AutoCompleteTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contextOfApplicationContext = getApplicationContext()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        loading = binding.loading
        menuAutocomplete = binding.menuAutocomplete
        val items = listOf("Operaciones 01", "Operaciones 02", "Operaciones 03", "Operaciones 04", "Operaciones 05", "Operaciones 06", "Operaciones 07", "Operaciones 08", "Operaciones 09", "Operaciones 10")
        val adapter = ArrayAdapter(this, R.layout.list_item_groups, items)
        menuAutocomplete!!.setAdapter(adapter)

        menuAutocomplete?.doAfterTextChanged {
            if(menuAutocomplete?.getEditableText().toString().length > 0){
                login.isEnabled = false
                if (username.editableText.toString().length > 0 && password.editableText.toString().length > 0) {
                    login.isEnabled = true
                }
            }
        }

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if(menuAutocomplete?.getEditableText().toString().length == 0){
                login.isEnabled = false
            }

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading!!.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        callLogin(
                            username.text.toString(),
                            password.text.toString()

                        )
                }
                false
            }

            login.setOnClickListener {
                loading!!.visibility = View.VISIBLE
                callLogin(username.text.toString(), password.text.toString())
            }
        }
    }
    private fun callLogin(username: String, password: String){
        val call = ApiInterface.create().setLogin(UserLogin(username, password))
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        call.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                val statusCode = response.code()
                val user = response.body()
                updateUiWithUserPw(user)
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                // Log error here since request failed
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                val editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
                editor.putBoolean("isLogin", false)
                editor.commit()

            }
        })

    }
    private fun updateUiWithUserPw(user: User?) {
        loading!!.visibility = View.INVISIBLE
        val welcome = getString(R.string.welcome)
        val displayName = user?.userDisplayName
        val group = menuAutocomplete?.getEditableText().toString()
        // TODO : initiate successful logged in experience
        val editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
        editor.putBoolean("isLogin", true)
        editor.putString("token", user?.token)
        editor.putString("email", user?.userEmail)
        editor.putString("name", user?.userDisplayName)
        editor.putString("group", group )

        editor.commit()

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
        setResult(Activity.RESULT_OK)
        Thread.sleep(1200)
        //Complete and destroy login activity once successful
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        val editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
        editor.putBoolean("isLogin", true)
        editor.putString("token", model.token)
        editor.putString("email", model.email)
        editor.putString("name", model.displayName)
        editor.commit()

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
        val editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
        editor.putBoolean("isLogin", false)
        editor.commit()
    }


    fun getContextOfApplication(): Context? {
        return contextOfApplicationContext
    }

}

private fun <T> Call<T>?.enqueue(callback: Callback<User>) {
    print("asdfasdf")
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}