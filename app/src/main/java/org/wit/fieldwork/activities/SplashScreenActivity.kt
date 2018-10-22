package org.wit.fieldwork.activities
/* Tutorial followed here ->>>> https://www.youtube.com/watch?v=6tIBaMd3A64
*/

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.wit.fieldwork.R
import org.wit.fieldwork.main.MainApp

class SplashScreenActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splashscreen)

    val background = object : Thread() {
      override fun run(){
        try {
          Thread.sleep(5000)

          val intent = Intent(baseContext, FieldworkListActivity::class.java)
          startActivity(intent)
        }catch (e:Exception){
          e.printStackTrace()
        }
      }
    }
    background.start()
  }


}