package org.wit.fieldwork.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
  return lastId++
}




class FieldworkMemStore : FieldworkStore, AnkoLogger {


  override fun findById(id: Long): FieldworkModel {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  val fieldworks = ArrayList<FieldworkModel>()

  override fun findAll(): List<FieldworkModel> {
    return fieldworks
  }





  override fun create(fieldwork: FieldworkModel) {
    fieldwork.id=getId()
    fieldworks.add(fieldwork)
    logAll()
  }

  override fun update(fieldwork: FieldworkModel) {
    var foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.id == fieldwork.id }
    if (foundFieldwork != null) {
      foundFieldwork.title = fieldwork.title
      foundFieldwork.description = fieldwork.description
      foundFieldwork.image = fieldwork.image
      foundFieldwork.lng = fieldwork.lng
      foundFieldwork.lat = fieldwork.lat
      foundFieldwork.zoom = fieldwork.zoom
      logAll()
    }
  }

  override fun delete(fieldwork: FieldworkModel) {
    fieldworks.remove(fieldwork)
  }


  fun clear() {
    fieldworks.clear()
  }


  fun logAll() {
    fieldworks.forEach{ info("${it}") }
  }
}