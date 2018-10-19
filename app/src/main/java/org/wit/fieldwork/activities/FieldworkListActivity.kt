package org.wit.fieldwork.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.*
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import kotlinx.android.synthetic.main.activity_fieldwork_list.*
import kotlinx.android.synthetic.main.card_fieldwork.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.fieldwork.adapters.FieldworkAdapter
import org.wit.fieldwork.adapters.FieldworkListener

class FieldworkListActivity : AppCompatActivity(), FieldworkListener {

  lateinit var app: MainApp

  private fun loadFieldworks() {
    showFieldworks(app.fieldworks.findAll())
  }

  fun showFieldworks (fieldworks: List<FieldworkModel>) {
    recyclerView.adapter = FieldworkAdapter(fieldworks, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_list)
    app = application as MainApp

    toolbarMain.title = title
    setSupportActionBar(toolbarMain)


    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    loadFieldworks()
  }


  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> startActivityForResult<FieldworkActivity>(0)
    }
    return super.onOptionsItemSelected(item)
  }


  override fun onFieldworkClick(fieldwork: FieldworkModel) {
    startActivityForResult(intentFor<FieldworkActivity>().putExtra("hillfort_edit",fieldwork), 0)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    loadFieldworks()
    super.onActivityResult(requestCode, resultCode, data)
  }
}




