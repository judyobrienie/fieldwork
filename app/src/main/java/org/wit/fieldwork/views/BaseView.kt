package org.wit.fieldwork.views

import android.content.Intent

import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.AnkoLogger

import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.editlocation.EditLocationView
import org.wit.fieldwork.views.map.FieldworkMapView
import org.wit.fieldwork.views.fieldwork.FieldworkView
import org.wit.fieldwork.views.fieldworklist.FieldworkListView

val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
  LOCATION, FIELDWORK, MAPS, LIST, SETTINGS
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

  var basePresenter: BasePresenter? = null

  fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
    var intent = Intent(this, FieldworkListView::class.java)
    when (view) {
      VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
      VIEW.FIELDWORK -> intent = Intent(this, FieldworkView::class.java)
      VIEW.MAPS -> intent = Intent(this, FieldworkMapView::class.java)
      VIEW.LIST -> intent = Intent(this, FieldworkListView::class.java)

    }
    if (key != "") {
      intent.putExtra(key, value)
    }
    startActivityForResult(intent, code)
  }

  fun initPresenter(presenter: BasePresenter): BasePresenter {
    basePresenter = presenter
    return presenter
  }

  fun init(toolbar: Toolbar) {
    toolbar.title = title
    setSupportActionBar(toolbar)
  }

  override fun onDestroy() {
    basePresenter?.onDestroy()
    super.onDestroy()
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (data != null) {
      basePresenter?.doActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  open fun showFieldwork(fieldwork: FieldworkModel) {}
  open fun showFieldworks(fieldworks: List<FieldworkModel>) {}
  open fun showProgress() {}
  open fun hideProgress() {}
}