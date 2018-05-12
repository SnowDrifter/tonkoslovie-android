package ru.romanov.tonkoslovie

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_activity.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import ru.romanov.tonkoslovie.data.eventbus.LogoutEvent
import ru.romanov.tonkoslovie.data.repositories.UserRepository
import ru.romanov.tonkoslovie.ui.screens.login.LoginActivity
import ru.romanov.tonkoslovie.ui.screens.login.dialogs.LogoutDialogFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val applicationComponent = TonkoslovieApplication.instance.applicationComponent
        applicationComponent.inject(this)

        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)

        nav_view.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        updateUserNavigationElements(nav_view.menu)

        EventBus.getDefault().register(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            updateUserNavigationElements(menu)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    fun sendMessage(view: View) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Заголовок")
                .setMessage("Текст")
                .setCancelable(false)
                .setNegativeButton("ОК!",
                        { dialog, _ -> dialog.cancel() })
        val alert = builder.create()
        alert.show()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "Настройки", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_lessons -> {
                Toast.makeText(applicationContext, "Уроки", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_themes -> {
                Toast.makeText(applicationContext, "Темы", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_settings -> {
                Toast.makeText(applicationContext, "Настройки", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.nav_logout -> {
                val dialog = LogoutDialogFragment()
                dialog.show(supportFragmentManager, "LogoutDialogFragment")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @Subscribe
    fun onLogoutEvent(logoutEvent: LogoutEvent) {
        userRepository.deleteToken()
        updateUserNavigationElements(nav_view.menu)
    }

    private fun updateUserNavigationElements(menu: Menu) {
        val authorized = userRepository.getToken().isNotEmpty()

        menu.findItem(R.id.nav_login)?.isVisible = !authorized
        menu.findItem(R.id.nav_registration)?.isVisible = !authorized
        menu.findItem(R.id.nav_logout)?.isVisible = authorized
    }

}
