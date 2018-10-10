package org.wit.fieldwork.activities

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
import org.jetbrains.anko.startActivityForResult

class FieldworkListActivity : AppCompatActivity() {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork_list)
    toolbarMain.title = title
    setSupportActionBar(toolbarMain)
    app = application as MainApp

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = FieldworkAdapter(app.fieldworks)
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

}
  class FieldworkAdapter constructor(private var fieldworks: List<FieldworkModel>) : RecyclerView.Adapter<FieldworkAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
      return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_fieldwork, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
      val fieldwork = fieldworks[holder.adapterPosition]
      holder.bind(fieldwork)
    }

    override fun getItemCount(): Int = fieldworks.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

      fun bind(fieldwork: FieldworkModel) {
        itemView.fieldworkTitle.text = fieldwork.title
        itemView.fieldworkDescription.text = fieldwork.description
      }
    }




  }
