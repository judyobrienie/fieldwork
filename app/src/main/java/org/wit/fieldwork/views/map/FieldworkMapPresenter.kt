package org.wit.fieldwork.views.map


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class FieldworkMapPresenter(view: BaseView) : BasePresenter(view) {


  fun doPopulateMap(map: GoogleMap, fieldworks: List<FieldworkModel>) {
    map.uiSettings.setZoomControlsEnabled(true)
    fieldworks.forEach {
      val loc = LatLng(it.location.lat, it.location.lng)
      val options = MarkerOptions().title(it.title).position(loc)
      map.addMarker(options).tag = it
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.location.zoom))
    }
  }

  fun doMarkerSelected(marker: Marker) {

    val fieldwork = marker.tag as FieldworkModel
    if (fieldwork != null) view?.showFieldwork(fieldwork)

  }

  fun loadFieldworks() {
    async(UI) {
      view?.showFieldworks(app.fieldworks.findAll())
    }
  }
}