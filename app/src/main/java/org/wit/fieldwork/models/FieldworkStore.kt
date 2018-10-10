package org.wit.fieldwork.models

interface FieldworkStore {
  fun findAll(): List<FieldworkModel>
  fun create(fieldwork: FieldworkModel)

  // to allow us to edit placemark
  //fun update(fieldwork: FieldworkModel)
}
