package org.wit.fieldwork.models


interface UserStore {

  fun findAll(): List<UserModel>
  fun create(userModel: UserModel)

  // to allow us to edit
  fun update(userModel: UserModel)


  // allow us to delete
  fun delete(userModel: UserModel)
}

