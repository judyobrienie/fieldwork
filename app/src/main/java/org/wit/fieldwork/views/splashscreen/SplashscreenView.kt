package org.wit.fieldwork.views.splashscreen



import android.os.Bundle
import org.wit.fieldwork.R
import org.wit.fieldwork.views.BaseView


class SplashscreenView : BaseView() {

  lateinit var presenter: SplashScreenPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splashscreen)

    presenter = initPresenter(SplashScreenPresenter(this))as SplashScreenPresenter

    val background = object : Thread() {
      override fun run(){
        try {
          Thread.sleep(5000)
          presenter.doGetLogin()

        }catch (e:Exception){
          e.printStackTrace()
        }
      }
    }
    background.start()
  }


}
