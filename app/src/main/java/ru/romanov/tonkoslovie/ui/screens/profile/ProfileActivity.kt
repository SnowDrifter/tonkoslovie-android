package ru.romanov.tonkoslovie.ui.screens.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.data.api.ApiCreator

class ProfileActivity  : AppCompatActivity() {

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
                            Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_SHORT).show()
                        },
                        { error ->
                            Toast.makeText(applicationContext, "Не удалось загрузить профиль", Toast.LENGTH_LONG).show()
                        }
                )
    }

}