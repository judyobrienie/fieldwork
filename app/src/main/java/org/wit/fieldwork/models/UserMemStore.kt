package org.wit.fieldwork.models


import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastid = 0L

internal fun getid(): Long {
  return lastid++
}

class UserMemStore : UserStore, AnkoLogger {
  override fun findByEmail(email: String): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun authenticate(email: String, password: String): UserModel? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  val users = ArrayList<UserModel>()

  override suspend fun findAll(): List<UserModel> {
    return users
  }

  override suspend fun create(user: UserModel) {
    user.idUser=getid()
    users.add(user)
    logAll()
  }




  override suspend fun update(user: UserModel) {
    var foundUser: UserModel? = users.find { p -> p.idUser == user.idUser }
    if (foundUser != null) {
      foundUser.name = user.name
      foundUser.email = user.email
      foundUser.password = user.password

      logAll()
    }
  }

  override suspend fun delete(user: UserModel) {
    users.remove(user)
  }


  fun logAll() {
    users.forEach{ info("${it}") }
  }
}