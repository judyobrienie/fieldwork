package org.wit.fieldwork.helpers

import android.app.Activity
import android.content.Intent
import org.wit.fieldwork.R

fun showImagePicker(parent: Activity, id: Int) {
  val intent = Intent()
  intent.type = "image/*"
  intent.action = Intent.ACTION_OPEN_DOCUMENT
  intent.addCategory(Intent.CATEGORY_OPENABLE)
  val chooser = Intent.createChooser(intent, R.string.select_fieldwork_image.toString())
  parent.startActivityForResult(chooser, id)
}