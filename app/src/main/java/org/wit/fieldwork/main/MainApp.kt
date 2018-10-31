package org.wit.fieldwork.main

import android.app.Application
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.*

class MainApp : Application(), AnkoLogger {

  lateinit var fieldworks: FieldworkStore
  lateinit var users: UserStore
  var loggedInUser = UserModel()


  override fun onCreate() {
    super.onCreate()
    users = UserJSONStore(applicationContext)
    fieldworks = FieldworkJSONStore(applicationContext)
    //fieldworks = FieldworkMemStore()




  }
}