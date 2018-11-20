
package org.wit.fieldwork.views.fieldworklist
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.VIEW

class FieldworkListPresenter(view: BaseView) : BasePresenter(view) {

  fun doAddFieldwork() {
    view?.navigateTo(VIEW.FIELDWORK)
  }

  fun doEditFieldwork(fieldwork: FieldworkModel) {
    view?.navigateTo(VIEW.FIELDWORK , 0, "hillfort_edit", fieldwork)
  }

  fun doShowFieldworksMap() {
    view?.navigateTo(VIEW.MAPS)
  }


 fun doGetSettings() {
    view?.navigateTo(VIEW.SETTINGS)
  }

  fun loadFieldworks() {
    view?.showFieldworks(app.fieldworks.findAll())
  }
}