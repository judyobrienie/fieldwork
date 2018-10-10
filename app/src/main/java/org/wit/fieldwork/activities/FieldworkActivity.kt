package org.wit.fieldwork.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    //set new toobar
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
    app=application as MainApp

    var edit = false
    if(intent.hasExtra("hillfort_edit")) {
      edit=true
      fieldwork = intent.extras.getParcelable<FieldworkModel>("hillfort_edit")
      fieldwork.title = fieldworkTitle.text.toString()
      fieldwork.description = fieldworkDescription.text.toString()
      btnAdd.setText(R.string.button_savePlacemark)
    }

    btnAdd.setOnClickListener() {
      fieldwork.title = fieldworkTitle.text.toString()
      fieldwork.description = fieldworkDescription.text.toString()

      if (fieldwork.title.isNotEmpty()) {
        if (edit){
          btnAdd.setText(R.string.button_savePlacemark)
          app.fieldworks.update(fieldwork.copy())
        }
        else{
          app.fieldworks.create(fieldwork.copy())
        }
        //set result of activity
        setResult(AppCompatActivity.RESULT_OK)
        //if button pressed again then finish
        finish()
      }
      else {
        toast (R.string.hint_enterATitle)
      }
    }

  }


  //inflate the menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_fieldworkactivity, menu)
    return super.onCreateOptionsMenu(menu)
  }

  //when the item on the menu is pressed - handling the event
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      //when cancel is pressed finish
      R.id.item_cancel ->
        finish()
    }
    return super.onOptionsItemSelected(item)
  }


}
