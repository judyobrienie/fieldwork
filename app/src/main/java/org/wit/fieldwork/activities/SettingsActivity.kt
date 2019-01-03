package org.wit.fieldwork.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.UserModel
import org.wit.fieldwork.views.fieldwork.FieldworkView


class SettingsActivity : AppCompatActivity(), AnkoLogger {
  lateinit var app: MainApp
 

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    toolbarMain.title = title
    setSupportActionBar(toolbarMain)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    app = application as MainApp





    fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menuInflater.inflate(R.menu.menu_main, menu)
      return super.onCreateOptionsMenu(menu)
    }

    fun onOptionsItemSelected(item: MenuItem?): Boolean {
      when (item?.itemId) {
        R.id.item_add -> startActivityForResult<FieldworkView>(0)
      }
      when (item?.itemId) {
        R.id.item_logout -> finish()
      }
      when (item?.itemId) {
        R.id.item_settings -> startActivityForResult<SettingsActivity>(0)
      }
      return super.onOptionsItemSelected(item)
    }

  }}