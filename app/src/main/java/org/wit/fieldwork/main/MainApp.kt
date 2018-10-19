package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.FieldworkJSONStore
import org.wit.fieldwork.models.FieldworkMemStore
import org.wit.fieldwork.models.FieldworkStore

class MainApp : Application(), AnkoLogger {

  lateinit var fieldworks: FieldworkStore

  override fun onCreate() {
    super.onCreate()
    fieldworks = FieldworkJSONStore(applicationContext)
    //fieldworks = FieldworkMemStore()

    info("Fieldwork Started")
  }
}