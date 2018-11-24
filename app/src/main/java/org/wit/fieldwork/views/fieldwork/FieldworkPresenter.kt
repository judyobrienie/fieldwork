package org.wit.fieldwork.views.fieldwork

import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.fieldwork.helpers.checkLocationPermissions
import org.wit.fieldwork.helpers.isPermissionGranted
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.Location
import org.wit.fieldwork.views.*


class FieldworkPresenter(view: BaseView) : BasePresenter(view){

  var map: GoogleMap? = null
  var locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view)

  var fieldwork = FieldworkModel()
  var defaultLocation = Location(52.245696, -7.139102, 15f)
  var edit = false

  init {
    if (view.intent.hasExtra("hillfort_edit")) {
      edit = true
      fieldwork = view.intent.extras.getParcelable<FieldworkModel>("hillfort_edit")
      view.showFieldwork(fieldwork)
    } else {
      if (checkLocationPermissions(view)) {
        doSetCurrentLocation()
      }
    }

  }


  fun locationUpdate(lat: Double, lng: Double) {
    fieldwork.lat = lat
    fieldwork.lng = lng
    fieldwork.zoom = 15f
    map?.clear()
    map?.uiSettings?.setZoomControlsEnabled(true)
    val options = MarkerOptions().title(fieldwork.title).position(LatLng(fieldwork.lat, fieldwork.lng))
    map?.addMarker(options)
    map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(fieldwork.lat, fieldwork.lng), fieldwork.zoom))
    view?.showFieldwork(fieldwork)
  }


    fun doConfigureMap(m: GoogleMap) {
      map = m
      locationUpdate(fieldwork.lat, fieldwork.lng)
    }


    override fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
      if (isPermissionGranted(requestCode, grantResults)) {
        doSetCurrentLocation()
      } else {
        // permissions denied, so use the default location
        locationUpdate(defaultLocation.lat, defaultLocation.lng)
      }
    }


  @SuppressLint("MissingPermission")
  fun doSetCurrentLocation() {
    locationService.lastLocation.addOnSuccessListener {
      locationUpdate(it.latitude, it.longitude)
    }
  }

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
    view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(fieldwork.lat, fieldwork.lng, fieldwork.zoom))
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
            locationUpdate(fieldwork.lat, fieldwork.lng)


        }
        }
      }



  }



