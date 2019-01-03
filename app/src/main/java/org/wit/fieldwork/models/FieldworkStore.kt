package org.wit.fieldwork.models

interface FieldworkStore {

  fun findAll(): List<FieldworkModel>

  fun findById(id:Long) : FieldworkModel

  fun create(fieldwork: FieldworkModel)

  // to allow us to edit
  fun update(fieldwork: FieldworkModel)

  fun clear()


  // allow us to delete
  fun delete(fieldwork: FieldworkModel)


}


