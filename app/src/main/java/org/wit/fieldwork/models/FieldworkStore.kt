package org.wit.fieldwork.models

import com.google.firebase.auth.FirebaseAuth

interface FieldworkStore {

  suspend fun findAll(): List<FieldworkModel>

  suspend fun findById(id:Long) : FieldworkModel?

  suspend fun create(fieldwork: FieldworkModel)

  // to allow us to edit
  suspend fun update(fieldwork: FieldworkModel)

  fun clear()


  // allow us to delete
 suspend fun delete(fieldwork: FieldworkModel)




}


