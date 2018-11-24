package org.wit.fieldwork.views.fieldwork

import android.content.Intent
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.Location
import org.wit.fieldwork.views.*


class FieldworkPresenter(view: BaseView) : BasePresenter(view){


  var fieldwork = FieldworkModel()

  var defaultLocation = Location(52.245696, -7.139102, 15f)
  var edit = false

  init {
    if (view.intent.hasExtra("hillfort_edit")) {
      edit = true
      fieldwork = view.intent.extras.getParcelable<FieldworkModel>("hillfort_edit")
          view.showFieldwork(fieldwork)
    }

  } //end of edit


  fun doAddorSave(title: String, description: String) {
    fieldwork.title = title
    fieldwork.description = description
      if (edit) {
        app.fieldworks.update(fieldwork.copy())
      } else {
        app.fieldworks.create(fieldwork.copy())
      }
      view?.finish()
    }


  fun doCancel() {
    view?.finish()
  }

  fun doDelete() {
    app.fieldworks.delete(fieldwork)
    view?.finish()
  }

  fun doSelectImage() {
    showImagePicker(view!!, IMAGE_REQUEST)
  }

  fun doSetLocation() {
    if (edit == false) {
      view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", defaultLocation)
    } else {
      view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(fieldwork.lat, fieldwork.lng, fieldwork.zoom))
    }
  }





  override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    when (requestCode) {
      IMAGE_REQUEST -> {
          fieldwork.image = data.data.toString()
          view?.showFieldwork(fieldwork)

            }

      LOCATION_REQUEST -> {
       val location = data.extras.getParcelable<Location>("location")
          fieldwork.lat = location.lat
          fieldwork.lng = location.lng
          fieldwork.zoom = location.zoom

        }
      }
    }



  }



