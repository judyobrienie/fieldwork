package org.wit.fieldwork.view.map

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
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.view.map.FieldworkMapPresenter

class FieldworkMapView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

  lateinit var presenter: FieldworkMapPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_maps)
    setSupportActionBar(toolbarMaps)
    presenter = FieldworkMapPresenter(this)

    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync {
      presenter.doPopulateMap(it)
    }
  }

  fun showFieldwork(fieldwork: FieldworkModel) {
    currentTitle.text = fieldwork.title
    currentDescription.text = fieldwork.description
    fieldworkImageView.setImageBitmap(readImageFromPath(this, fieldwork.image))
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    presenter.doMarkerSelected(marker)
    return true
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