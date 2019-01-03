package org.wit.fieldwork.views.map

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_fieldwork_maps.*
import kotlinx.android.synthetic.main.card_fieldwork.*
import kotlinx.android.synthetic.main.content_fieldwork_maps.*
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BaseView

class FieldworkMapView : BaseView(), GoogleMap.OnMarkerClickListener {

  lateinit var presenter: FieldworkMapPresenter
  lateinit var map: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_maps)
    super.init(toolbarMaps, false)

    presenter = initPresenter (FieldworkMapPresenter(this)) as FieldworkMapPresenter

    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync {
      map = it
      map.setOnMarkerClickListener(this)
      presenter.loadFieldworks()
    }
  }

 override fun showFieldwork(fieldwork: FieldworkModel) {
    currentTitle.text = fieldwork.title
    currentDescription.text = fieldwork.description
     Glide.with(this).load(fieldwork.image).into(fieldworkImageView);
  }


  override fun showFieldworks(fieldworks: List<FieldworkModel>) {
    presenter.doPopulateMap(map, fieldworks)
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