package org.wit.fieldwork.views.fieldwork

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BaseView


class FieldworkView : BaseView(), AnkoLogger {

  lateinit var presenter: FieldworkPresenter
  var fieldwork = FieldworkModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork)

    init(toolbarAdd, false)

    presenter = initPresenter (FieldworkPresenter(this)) as FieldworkPresenter

    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync {
      presenter.doConfigureMap(it)
      it.setOnMapClickListener { presenter.doSetLocation() }
    }



    chooseImage.setOnClickListener { presenter.doSelectImage() }


  }


  override fun showFieldwork(fieldwork: FieldworkModel) {
    fieldworkTitle.setText(fieldwork.title)
    fieldworkDescription.setText(fieldwork.description)
    Glide.with(this).load(fieldwork.image).into(fieldworkImage);
    if (fieldwork.image != null) {
      chooseImage.setText(R.string.button_saveImage)
    }
    lat.setText("%.6f".format(fieldwork.location.lat))
    lng.setText("%.6f".format(fieldwork.location.lng))
  }



override fun onCreateOptionsMenu(menu: Menu): Boolean {
  menuInflater.inflate(R.menu.menu_fieldworkactivity, menu)
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


  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
    presenter.doRestartLocationUpdates()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }



}


