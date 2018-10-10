package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.FieldworkModel

class MainApp : Application(), AnkoLogger {

  val fieldworks = ArrayList<FieldworkModel>()

  override fun onCreate() {
    super.onCreate()
    info("Fieldmark started")

    fieldworks.add(FieldworkModel("One", "About one..."))
    fieldworks.add(FieldworkModel("One", "About one..."))

  }
}