package org.wit.fieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.AnkoLogger
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp


class SettingsActivity : AppCompatActivity(), AnkoLogger {

  lateinit var app: MainApp
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    app = application as MainApp

    emailView.setText("placeholder")
    passwordView.setText("placeholder")
}
}