package ru.romanov.tonkoslovie.ui.screens.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.romanov.tonkoslovie.MainActivity
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.TonkoslovieApplication
import ru.romanov.tonkoslovie.data.api.ApiCreator
import ru.romanov.tonkoslovie.data.models.user.UserRequest
import ru.romanov.tonkoslovie.data.repositories.UserRepository
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val applicationComponent = TonkoslovieApplication.instance.applicationComponent
        applicationComponent.inject(this)
    }

    fun sendLogin(view: View) {
        val email = findViewById<EditText>(R.id.login_email).text.toString()
        val password = findViewById<EditText>(R.id.login_password).text.toString()

        val request = UserRequest(email, password)

        val api = ApiCreator.getUserApi()
        val response = api.login(request)

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            userRepository.saveToken(result.token)
                            startActivity(Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        },
                        { error -> Toast.makeText(applicationContext, "Error!\nMessage: ${error.message}", Toast.LENGTH_SHORT).show() }
                )
    }
}
