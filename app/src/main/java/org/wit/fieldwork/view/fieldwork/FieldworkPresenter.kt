package org.wit.fieldwork.view.fieldwork

import android.content.Intent


import org.jetbrains.anko.intentFor
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.Location
import org.wit.fieldwork.view.editlocation.EditLocationView


class FieldworkPresenter(val view: FieldworkView){


  var fieldwork = FieldworkModel()
  lateinit var app: MainApp
  var location = Location(52.245696, -7.139102, 15f)
  val LOCATION_REQUEST = 2
  val IMAGE_REQUEST = 1
  var edit = false

  init {
    app = view.application as MainApp
    if (view.intent.hasExtra("hillfort_edit")) {
      edit = true
      fieldwork = view.intent.extras.getParcelable<FieldworkModel>("hillfort_edit")
          view.showFieldwork(fieldwork)
    }





  } //end of edit


  fun doAddorSave(title: String, description: String) {
    fieldwork.title = title
    fieldwork.description = description

    if (title.isNotEmpty()) {
      if (edit)
        app.fieldworks.update(fieldwork.copy())
      } else {
        app.fieldworks.create(fieldwork.copy())
      }
      view.finish()
  }

  fun doCancel() {
    view.finish()
  }

  fun doDelete() {
    app.fieldworks.delete(fieldwork)
    view.finish()
  }

  fun doSelectImage() {
    showImagePicker(view, IMAGE_REQUEST)
  }

  fun doSetLocation() {
    if (fieldwork.zoom != 0f) {
      location.lat = fieldwork.lat
      location.lng = fieldwork.lng
      location.zoom = fieldwork.zoom
    }
    view.startActivityForResult(view.intentFor<EditLocationView>().putExtra("location", location), LOCATION_REQUEST)
  }





  fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    when (requestCode) {
      IMAGE_REQUEST -> {
          fieldwork.image = data.data.toString()
          view.showFieldwork(fieldwork)
          fieldwork.image = data.data.toString()
          view.showFieldwork(fieldwork)

            }

      LOCATION_REQUEST -> {
       location = data.extras.getParcelable<Location>("location")
          fieldwork.lat = location.lat
          fieldwork.lng = location.lng
          fieldwork.zoom = location.zoom

        }
      }
    }



  }



