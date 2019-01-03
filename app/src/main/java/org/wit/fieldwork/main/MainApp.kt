package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.*
import org.wit.fieldwork.models.json.FieldworkJSONStore
import org.wit.fieldwork.models.room.FieldworkStoreRoom

class MainApp : Application(), AnkoLogger {

  lateinit var fieldworks: FieldworkStore
  //lateinit var users: UserStore
 // var loggedInUser = UserModel()


  override fun onCreate() {
    super.onCreate()
   // users = UserJSONStore(applicationContext)
    //fieldworks = FieldworkStoreRoom(applicationContext)
    //fieldworks = FieldworkMemStore()

    fieldworks = FieldworkFireStore(applicationContext)
    info("Placemark started")




  }
}