package org.wit.fieldwork.views.splashscreen


import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.VIEW


class SplashScreenPresenter (view: BaseView) : BasePresenter(view) {


  fun doGetLogin() {
    view?.navigateTo(VIEW.LOGIN)
  }
}