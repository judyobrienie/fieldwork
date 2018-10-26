package org.wit.fieldwork.activities

import android.content.Intent
import android.net.Uri
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
import android.R.attr.data
import android.content.ClipData
import android.R.attr.data
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout


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
      for (item in fieldwork.images) {
        var imageView = ImageView(applicationContext)
 var linearLayout = LinearLayout(this);
        imageView.setImageBitmap(readImageFromPath(this, item))
        linearLayout.addView(imageView)
//make visible to program
//        setContentView(linearLayout)
//        fieldworkImage.setImageBitmap(readImageFromPath(this, fieldwork.images[0]))
      }

      //setting location
     // location.lat = fieldwork.lat
     // location.lng = fieldwork.lng
     // location.zoom = fieldwork.zoom

   //   }
    }

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
              val uri = data.clipData.getItemAt(i).uri.toString()
              fieldwork.images.add(uri)
              fieldwork.image = fieldwork.images[0].toString()
              fieldworkImage.setImageBitmap(readImage(this, resultCode, data))
            }

          }
            else{ //single image
            val uri = data?.data.toString()
            fieldwork.images.clear()
            fieldwork.images.add(uri)
            fieldwork.image = data.getData().toString()
           
            fieldworkImage.setImageBitmap(readImage(this, resultCode, data))
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


