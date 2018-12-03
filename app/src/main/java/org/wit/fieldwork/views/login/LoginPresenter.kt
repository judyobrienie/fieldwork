package org.wit.fieldwork.views.login


import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {

  fun doLogin(email: String, password: String) {
    view?.navigateTo(VIEW.LIST)
  }

  fun doSignUp(email: String, password: String) {
    view?.navigateTo(VIEW.LIST)
  }
}