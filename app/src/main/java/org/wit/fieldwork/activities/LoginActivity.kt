package org.wit.fieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.UserModel

class LoginActivity : AppCompatActivity(), AnkoLogger {
  lateinit var app: MainApp
  private var user = UserModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    toolbarLogin.title = title
    setSupportActionBar(toolbarLogin)
    app = application as MainApp

    app.users.findAll().forEach { info("User: $it\n") }

    btnLogin.setOnClickListener {
      user.email = userEmail.text.toString()
      user.password = userPassword.text.toString()

      if (user.email.isNotEmpty() && user.password.isNotEmpty()) {
        val foundUser = app.users.authenticate(user.email, user.password)

        // Check if user exists
        if (foundUser != null) {
          app.loggedInUser = foundUser
          startActivityForResult(intentFor<FieldworkListActivity>(), 0)
        }
        // Otherwise create a new user
        else {
          app.users.create(user)
          app.loggedInUser = user
          startActivityForResult(intentFor<FieldworkListActivity>(), 0)
        }
      } else {
        toast("Invalid credentials")
      }
    }
  }

  // Inflate the menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }
}