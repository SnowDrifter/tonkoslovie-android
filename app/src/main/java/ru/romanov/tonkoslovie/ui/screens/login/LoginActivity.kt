package ru.romanov.tonkoslovie.ui.screens.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.data.models.user.UserRequest
import ru.romanov.tonkoslovie.data.models.user.api.UserApiService


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }

    fun sendLogin(view: View) {
        val email = findViewById<EditText>(R.id.login_email).text.toString()
        val password = findViewById<EditText>(R.id.login_password).text.toString()

        val request = UserRequest(email, password)

        val api = UserApiService.create()
        val response = api.login(request)

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Toast.makeText(applicationContext, "Success!\nToken: ${result.token}", Toast.LENGTH_SHORT).show() },
                        { error -> Toast.makeText(applicationContext, "Error!\nMessage: ${error.message}", Toast.LENGTH_SHORT).show() }
                )
    }
}
