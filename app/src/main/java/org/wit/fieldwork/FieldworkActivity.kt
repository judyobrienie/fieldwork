package org.wit.fieldwork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast


class FieldworkActivity : AppCompatActivity(), AnkoLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork)
    info("Placemark Activity started..")

    btnAdd.setOnClickListener() {
      val placemarkTitle = fieldworkTitle.text.toString()
      if (placemarkTitle.isNotEmpty()) {
        info("add Button Pressed: $placemarkTitle")
      }
      else {
        toast ("Please Enter a title")
      }
    }

  }
}
