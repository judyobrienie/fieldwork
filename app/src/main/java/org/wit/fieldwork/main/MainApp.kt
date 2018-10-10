package org.wit.fieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

  override fun onCreate() {
    super.onCreate()
    info("Fieldmark started")
  }
}