package ru.romanov.tonkoslovie.ui.screens.profile

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.data.api.ApiCreator

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
    }

    fun loadProfile(view: View) {
        val api = ApiCreator.getUserApi()
        val response = api.user()

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            val userNameInput = findViewById<TextInputEditText>(R.id.profile_input_username)
                            userNameInput.setText(result.username)

                            val firstNameInput = findViewById<TextInputEditText>(R.id.profile_input_firstName)
                            firstNameInput.setText(result.firstName)

                            val lastNameInput = findViewById<TextInputEditText>(R.id.profile_input_lastName)
                            lastNameInput.setText(result.lastName)
                        },
                        { error ->
                            Toast.makeText(applicationContext, "Не удалось загрузить профиль", Toast.LENGTH_LONG).show()
                        }
                )
    }

}