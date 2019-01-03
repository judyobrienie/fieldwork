package org.wit.fieldwork.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.FieldworkStore

var lastId = 0L

internal fun getId(): Long {
  return lastId++
}




class FieldworkMemStore : FieldworkStore, AnkoLogger {


  suspend override fun findById(id: Long): FieldworkModel {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  val fieldworks = ArrayList<FieldworkModel>()

  suspend override fun findAll(): List<FieldworkModel> {
    return fieldworks
  }





  suspend override fun create(fieldwork: FieldworkModel) {
    fieldwork.id= getId()
    fieldworks.add(fieldwork)
    logAll()
  }

  suspend override fun update(fieldwork: FieldworkModel) {
    var foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.id == fieldwork.id }
    if (foundFieldwork != null) {
      foundFieldwork.title = fieldwork.title
      foundFieldwork.description = fieldwork.description
      foundFieldwork.image = fieldwork.image
      foundFieldwork.location = fieldwork.location
      logAll()
    }
  }

  suspend override fun delete(fieldwork: FieldworkModel) {
    fieldworks.remove(fieldwork)
  }


  override fun clear() {
    fieldworks.clear()
  }


  fun logAll() {
    fieldworks.forEach{ info("${it}") }
  }
}