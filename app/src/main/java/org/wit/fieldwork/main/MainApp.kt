package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.wit.fieldwork.models.*
import org.wit.fieldwork.models.json.FieldworkJSONStore

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