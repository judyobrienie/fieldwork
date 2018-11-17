package org.wit.fieldwork.view.fieldworklist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel
import kotlinx.android.synthetic.main.activity_fieldwork_list.*


class FieldworkListView : AppCompatActivity(), FieldworkListener {

  lateinit var presenter: FieldworkListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_list)
    toolbarMain.title = title
    setSupportActionBar(toolbarMain)

    presenter = FieldworkListPresenter(this)
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = FieldworkAdapter(presenter.getFieldoworks(), this)
    recyclerView.adapter?.notifyDataSetChanged()
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



  override fun onFieldworkClick(fieldwork: FieldworkModel) {
    presenter.doEditFieldwork(fieldwork)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    recyclerView.adapter?.notifyDataSetChanged()
    super.onActivityResult(requestCode, resultCode, data)
  }
}




