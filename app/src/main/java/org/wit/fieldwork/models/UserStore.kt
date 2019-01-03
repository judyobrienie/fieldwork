package org.wit.fieldwork.models


interface UserStore {

  suspend fun findAll(): List<UserModel>
  suspend fun create(userModel: UserModel)

  // to allow us to edit
  suspend fun update(userModel: UserModel)

  // allow us to delete
  suspend fun delete(userModel: UserModel)

  // check if a user exists
  fun findByEmail(email: String): Boolean

  // authenticate a user
  fun authenticate(email: String, password: String): UserModel?
}
