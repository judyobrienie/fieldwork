package org.wit.fieldwork.models


interface UserStore {

  fun findAll(): List<UserModel>
  fun create(userModel: UserModel)

  // to allow us to edit
  fun update(userModel: UserModel)

  // allow us to delete
  fun delete(userModel: UserModel)

  // check if a user exists
  fun findByEmail(email: String): Boolean

  // authenticate a user
  fun authenticate(email: String, password: String): UserModel?
}
