package org.wit.fieldwork.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp

class FieldworkListActivity : AppCompatActivity() {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_list)
    app = application as MainApp
  }
}