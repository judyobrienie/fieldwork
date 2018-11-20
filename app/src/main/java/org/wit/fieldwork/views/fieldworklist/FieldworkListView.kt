package org.wit.fieldwork.views.fieldworklist

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import org.wit.fieldwork.views.BaseView


class FieldworkListView : BaseView(), FieldworkListener {

  lateinit var presenter: FieldworkListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_list)
    init(toolbarMain)

    presenter = initPresenter(FieldworkListPresenter(this)) as FieldworkListPresenter

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadFieldworks()
  }



  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> presenter.doAddFieldwork()
      R.id.item_map -> presenter.doShowFieldworksMap()
      R.id.item_logout -> finish()
      R.id.item_settings -> presenter.doGetSettings()
    }
    return super.onOptionsItemSelected(item)
  }


  override fun showFieldworks(fieldworks: List<FieldworkModel>) {
    recyclerView.adapter = FieldworkAdapter(fieldworks, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }


  override fun onFieldworkClick(fieldwork: FieldworkModel) {
    presenter.doEditFieldwork(fieldwork)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    presenter.loadFieldworks()
    super.onActivityResult(requestCode, resultCode, data)
  }
}




