package org.wit.fieldwork.models


import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastid = 0L

internal fun getid(): Long {
  return lastid++
}

class UserMemStore : UserStore, AnkoLogger {

  val users = ArrayList<UserModel>()

  override fun findAll(): List<UserModel> {
    return users
  }


  override fun create(user: UserModel) {
    user.idUser=getid()
    users.add(user)
    logAll()
  }




  override fun update(user: UserModel) {
    var foundUser: UserModel? = users.find { p -> p.idUser == user.idUser }
    if (foundUser != null) {
      foundUser.name = user.name
      foundUser.email = user.email
      foundUser.password = user.password

      logAll()
    }
  }

  override fun delete(user: UserModel) {
    users.remove(user)
  }


  fun logAll() {
    users.forEach{ info("${it}") }
  }
}