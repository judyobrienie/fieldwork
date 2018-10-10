package org.wit.fieldwork.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel


class FieldworkActivity : AppCompatActivity(), AnkoLogger {

  val fieldworks = ArrayList<FieldworkModel>()
  var fieldwork = FieldworkModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork)
    info("Fieldwork Activity started..")

    btnAdd.setOnClickListener() {
      fieldwork.title = fieldworkTitle.text.toString()
      fieldwork.description = fieldworkDescription.text.toString()
      if (fieldwork.title.isNotEmpty() && fieldwork.description.isNotEmpty()) {
        info("array: $fieldworks")
        fieldworks.add(fieldwork.copy())
      }
      else {
        toast ("Please Enter a title")
      }
    }

  }
}
