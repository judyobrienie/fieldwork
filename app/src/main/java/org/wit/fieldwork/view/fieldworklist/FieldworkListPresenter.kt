package org.wit.fieldwork.view.fieldworklist

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.view.fieldwork.FieldworkView
import org.wit.fieldwork.activities.SettingsActivity
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.view.map.FieldworkMapView

class FieldworkListPresenter(val view: FieldworkListView) {

  var app: MainApp

  init {
    app = view.application as MainApp
  }

  fun getFieldoworks() = app.fieldworks.findAll()

  fun doAddFieldwork() {
    view.startActivityForResult<FieldworkView>(0)
  }

  fun doGetSettings() {
    view.startActivityForResult<SettingsActivity>(0)
  }

  fun doEditFieldwork(fieldwork: FieldworkModel) {
    view.startActivityForResult(view.intentFor<FieldworkView>().putExtra("fieldwork_edit", fieldwork), 0)
  }

  fun doShowFieldworksMap() {
    view.startActivity<FieldworkMapView>()
  }
}