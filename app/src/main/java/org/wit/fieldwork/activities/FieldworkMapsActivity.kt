package org.wit.fieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_fieldwork_maps.*
import kotlinx.android.synthetic.main.content_fieldwork_maps.*
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.main.MainApp

class FieldworkMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener  {

  lateinit var map: GoogleMap
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_maps)
    setSupportActionBar(toolbarMaps)
    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync {
      map = it
      configureMap()
    }

    app = application as MainApp
  }


  fun configureMap() {
    map.uiSettings.setZoomControlsEnabled(true)
    app.fieldworks.findAll().forEach {
      val loc = LatLng(it.lat, it.lng)
      val options = MarkerOptions().title(it.title).position(loc)
      map.addMarker(options).tag = it.id
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))

    }
    map.setOnMarkerClickListener(this)
  }


  override fun onMarkerClick(marker: Marker): Boolean {
    val tag = marker.tag as Long
    val fieldwork = app.fieldworks.findById(tag)
    currentTitle.text = marker.title
    fieldworkImageView.setImageBitmap(readImageFromPath(this, fieldwork.images[0]))
    return false
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
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }
}
