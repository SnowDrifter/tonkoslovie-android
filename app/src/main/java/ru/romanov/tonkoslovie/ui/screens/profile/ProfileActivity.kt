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
import ru.romanov.tonkoslovie.data.models.user.User

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

    fun updateProfile(view: View) {
        val username = findViewById<EditText>(R.id.profile_input_username).text.toString()
        val firstName = findViewById<EditText>(R.id.profile_input_firstName).text.toString()
        val lastName = findViewById<EditText>(R.id.profile_input_lastName).text.toString()

        val api = ApiCreator.getUserApi()
        val request = User(username, firstName, lastName)

        val response = api.update(request)

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Toast.makeText(applicationContext, "OK", Toast.LENGTH_SHORT).show()
                        },
                        { error -> Toast.makeText(applicationContext, "Error!\nMessage: ${error.message}", Toast.LENGTH_SHORT).show() }
                )

    }

}