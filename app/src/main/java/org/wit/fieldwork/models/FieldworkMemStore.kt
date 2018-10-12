package org.wit.fieldwork.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
  return lastId++
}

class FieldworkMemStore : FieldworkStore, AnkoLogger {

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

  fun logAll() {
    fieldworks.forEach{ info("${it}") }
  }
}