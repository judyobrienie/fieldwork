package org.wit.fieldwork.models

interface FieldworkStore {
  fun findAll(): List<FieldworkModel>
  fun create(fieldwork: FieldworkModel)

  // to allow us to edit
  fun update(fieldwork: FieldworkModel)

  // allow us to delete
  fun delete(fieldwork: FieldworkModel)
}
