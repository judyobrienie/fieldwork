
package org.wit.fieldwork.views.fieldworklist
import com.google.firebase.auth.FirebaseAuth
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.views.BasePresenter
import org.wit.fieldwork.views.BaseView
import org.wit.fieldwork.views.VIEW
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

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

    fun doLogout() {
        FirebaseAuth.getInstance().signOut()
        app.fieldworks.clear()
        view?.navigateTo(VIEW.LOGIN)
    }


 fun doGetSettings() {
    view?.navigateTo(VIEW.SETTINGS)
  }

   fun loadFieldworks() {
      async(UI) {
          view?.showFieldworks(app.fieldworks.findAll())
      }
  }
}