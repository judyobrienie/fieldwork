package org.wit.fieldwork.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_fieldwork.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImage
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.helpers.showImagePicker
import org.wit.fieldwork.main.MainApp
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.Location


class FieldworkActivity : AppCompatActivity(), AnkoLogger {


  var fieldwork = FieldworkModel()
  lateinit var app: MainApp
  val IMAGE_REQUEST = 1
  val LOCATION_REQUEST = 2
 // var location = Location(52.245696, -7.139102, 15f)

  override fun onCreate(savedInstanceState: Bundle?) {


    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fieldwork)



    //set new toobar
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)


    supportActionBar!!.title = "Go Back"
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    app = application as MainApp



    var edit = false
    if (intent.hasExtra("hillfort_edit")) {
      edit = true
      fieldwork = intent.extras.getParcelable<FieldworkModel>("hillfort_edit")
      fieldworkTitle.setText(fieldwork.title)
      fieldworkDescription.setText(fieldwork.description)
      btnAdd.setText(R.string.button_saveFieldwork)
      if (fieldwork.image != null) {
        chooseImage.setText(R.string.button_saveImage)
      }

//
// imges from array of images
      if (fieldwork.images.size == 1) {
        fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
      }
      if (fieldwork.images.size == 2) {
        fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
        fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.images.get(1)))
      }
      if (fieldwork.images.size == 3) {
        fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
        fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.images.get(1)))
        fieldworkImage3.setImageBitmap(readImageFromPath(this, fieldwork.images.get(2)))
      }
      if (fieldwork.images.size == 4) {
        fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
        fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.images.get(1)))
        fieldworkImage3.setImageBitmap(readImageFromPath(this, fieldwork.images.get(2)))
        fieldworkImage4.setImageBitmap(readImageFromPath(this, fieldwork.images.get(3)))
      }


//        Debugging

info("images: " + fieldwork.images.toString())
info("images: " + fieldwork.images.get(0))
info("images: " + readImageFromPath(this, fieldwork.images.get(0)))

    } //end of edit



    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }

    fieldworkLocation.setOnClickListener {
      val location = Location(52.245696, -7.139102, 15f)
      if (fieldwork.zoom != 0f) {
        location.lat =  fieldwork.lat
        location.lng = fieldwork.lng
        location.zoom = fieldwork.zoom
      }
      startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
    }

    btnAdd.setOnClickListener() {
      fieldwork.title = fieldworkTitle.text.toString()
      fieldwork.description = fieldworkDescription.text.toString()

      


      if (fieldwork.title.isNotEmpty()) {
        if (edit) {
          btnAdd.setText(R.string.button_saveFieldwork)
          app.fieldworks.update(fieldwork.copy())
        } else {
          app.fieldworks.create(fieldwork.copy())
        }
        //set result of activity
        setResult(AppCompatActivity.RESULT_OK)
        //if button pressed again then finish
        finish()
      } else {
        toast(R.string.hint_enterATitle)
      }
    }

  }


  //inflate the menu
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_fieldworkactivity, menu)
    return super.onCreateOptionsMenu(menu)
  }

  //when the item on the menu is pressed - handling the event
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      //when cancel is pressed finish
      R.id.item_cancel ->
        finish()
    }
    when (item?.itemId) {
      //when cancel is pressed finish
      R.id.item_delete -> {
        app.fieldworks.delete(fieldwork)
        finish()
        }
    }
    return super.onOptionsItemSelected(item)
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      IMAGE_REQUEST -> {

        if (data != null) {
          chooseImage.setText(R.string.button_saveImage)

          val imagesPath = data.clipData  /// multiple images
          if (imagesPath != null) {
            fieldwork.images.clear()
            for (i in 0 until data.clipData.itemCount) {
              val uri = data.clipData.getItemAt(i).uri
              fieldwork.images.add(uri.toString())
//
            }

            if (fieldwork.images.size == 1) {
              fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
            }
            if (fieldwork.images.size == 2) {
              fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
              fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.images.get(1)))
            }
            if (fieldwork.images.size == 3) {
              fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
              fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.images.get(1)))
              fieldworkImage3.setImageBitmap(readImageFromPath(this, fieldwork.images.get(2)))
            }
            if (fieldwork.images.size == 4) {
              fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images.get(0)))
              fieldworkImage2.setImageBitmap(readImageFromPath(this, fieldwork.images.get(1)))
              fieldworkImage3.setImageBitmap(readImageFromPath(this, fieldwork.images.get(2)))
              fieldworkImage4.setImageBitmap(readImageFromPath(this, fieldwork.images.get(3)))
            }

            //testing image loading
            //fieldworkImage.setImageBitmap(readImage(this, resultCode, data.clipData.getItemAt(0).uri))
           // fieldworkImage2.setImageBitmap(readImage(this, resultCode, data.clipData.getItemAt(1).uri))
           // fieldworkImage3.setImageBitmap(readImage(this, resultCode, data.clipData.getItemAt(2).uri))
           // fieldworkImage4.setImageBitmap(readImage(this, resultCode, data.clipData.getItemAt(3).uri))

          }
            else{ //single image
            val uri = data?.data
            fieldwork.images.clear()
            fieldwork.images.add(uri.toString())
            fieldwork.image = data.getData().toString()

            fieldworkImage.setImageBitmap(readImage(this, resultCode, uri))
            }
         }
        }


      LOCATION_REQUEST -> {
        if (data != null) {
          val location = data.extras.getParcelable<Location>("location")
          fieldwork.lat = location.lat
          fieldwork.lng = location.lng
          fieldwork.zoom = location.zoom
          info("Location: $location")
        }
      }
    }
  }
}


