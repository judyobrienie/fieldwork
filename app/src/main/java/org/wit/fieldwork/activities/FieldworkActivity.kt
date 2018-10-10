package org.wit.fieldwork.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel


class FieldworkActivity : AppCompatActivity(), AnkoLogger {


  var fieldwork = FieldworkModel()
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork)
    app=application as MainApp

    btnAdd.setOnClickListener() {
      fieldwork.title = fieldworkTitle.text.toString()
      fieldwork.description = fieldworkDescription.text.toString()
      if (fieldwork.title.isNotEmpty() && fieldwork.description.isNotEmpty()) {
       app!!.fieldworks.add(fieldwork.copy())
        info("add Button Pressed: $fieldworkTitle")
        app!!.fieldworks.forEach {info("add Button Pressed: ${it}")}
      }
      else {
        toast ("Please Enter a title")
      }
    }

  }
}
