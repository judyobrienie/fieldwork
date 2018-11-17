package org.wit.fieldwork.view.fieldwork

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.R.id.item_save
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel


class FieldworkView : AppCompatActivity(), AnkoLogger {

  lateinit var presenter: FieldworkPresenter
  var fieldwork = FieldworkModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork)
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
    supportActionBar!!.title = "Go Back"
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    presenter = FieldworkPresenter(this)

    chooseImage.setOnClickListener { presenter.doSelectImage() }

    fieldworkLocation.setOnClickListener { presenter.doSetLocation() }

  }


  fun showFieldwork(fieldwork: FieldworkModel) {
    fieldworkTitle.setText(fieldwork.title)
    fieldworkDescription.setText(fieldwork.description)
    fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.image))
    if (fieldwork.image != null) {
      chooseImage.setText(R.string.button_saveImage)
    }

  }



override fun onCreateOptionsMenu(menu: Menu): Boolean {
  menuInflater.inflate(R.menu.menu_fieldworkactivity, menu)
  if (presenter.edit) menu.getItem(0).setVisible(true)
  return super.onCreateOptionsMenu(menu)
}




  //when the item on the menu is pressed - handling the event
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> { presenter.doCancel() }
      R.id.item_delete -> { presenter.doDelete() }
      R.id.item_save -> {
        if (fieldworkTitle.text.toString().isEmpty()) {
          toast(R.string.hint_enterATitle)
        } else {
          presenter.doAddorSave(fieldworkTitle.text.toString(), fieldworkDescription.text.toString())
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (data != null) {
      presenter.doActivityResult(requestCode, resultCode, data)
    }
  }





}


