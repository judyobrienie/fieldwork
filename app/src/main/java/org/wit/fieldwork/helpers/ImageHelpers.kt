package org.wit.fieldwork.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import org.wit.fieldwork.R
import java.io.IOException


fun showImagePicker(parent: Activity, id: Int) {
  val intent = Intent()
  intent.type = "image/*"
  intent.action = Intent.ACTION_OPEN_DOCUMENT
  intent.addCategory(Intent.CATEGORY_OPENABLE)
  intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
  val chooser = Intent.createChooser(intent, R.string.select_fieldwork_image.toString())
  parent.startActivityForResult(chooser, id)
}

fun readImage(activity: Activity, resultCode: Int, data: Uri?): Bitmap? {
  var bitmap: Bitmap? = null
  if (resultCode == Activity.RESULT_OK && data != null && data != null ) {
    try {
      bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, data)
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }
  return bitmap
}

fun readImageFromPath(context: Context, path : String) : Bitmap? {
  var bitmap : Bitmap? = null
  val uri = Uri.parse(path)
  if (uri != null) {
    try {
      val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")
      val fileDescriptor = parcelFileDescriptor.getFileDescriptor()
      bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
      parcelFileDescriptor.close()
    } catch (e: Exception) {
    }
  }
  return bitmap
}