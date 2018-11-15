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

    app = application as MainApp

    app.users.findAll().forEach { info("User: $it\n") }

    btnLogin.setOnClickListener {
      user.email = userEmail.text.toString()
      user.password = userPassword.text.toString()
      user.name = userEmail.text.toString()

      if (user.email.isNotEmpty() && user.password.isNotEmpty()) {
        val foundUser = app.users.authenticate(user.email, user.password)
        val foundEmail = app.users.findByEmail(user.email)

        // Check if user exists
        if (foundUser != null) {
          app.loggedInUser = foundUser
          startActivityForResult(intentFor<FieldworkListActivity>(), 0)
        } else {
          toast("Invalid credentials")
        }


      }
    }
        // Otherwise create a new user
    btnNewUser.setOnClickListener {
      user.email = userEmail.text.toString()
      user.password = userPassword.text.toString()
      user.name = userEmail.text.toString()

      if (user.email.isNotEmpty() && user.password.isNotEmpty()) {
        val foundEmail = app.users.findByEmail(user.email)
        if (!foundEmail) {
          app.users.create(user)
          app.loggedInUser = user
          startActivityForResult(intentFor<FieldworkListActivity>(), 0)
        }
        else{
          toast("Email already registered")
        }
      }
    }
  }


}