package org.wit.fieldwork.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class FieldworkMemStore : FieldworkStore, AnkoLogger {

  val fieldworks = ArrayList<FieldworkModel>()

  override fun findAll(): List<FieldworkModel> {
    return fieldworks
  }


  override fun create(fieldwork: FieldworkModel) {
    fieldworks.add(fieldwork)
    logAll()
  }

  fun logAll() {
    fieldworks.forEach{ info("${it}") }
  }
}