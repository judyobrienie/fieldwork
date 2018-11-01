
package org.wit.fieldwork.models
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.fieldwork.helpers.exists
import org.wit.fieldwork.helpers.read
import org.wit.fieldwork.helpers.write
import java.util.*





val JSON_FILEs = "users.json"
val gsonBuilders = GsonBuilder().setPrettyPrinting().create()
val listTypes = object : TypeToken<ArrayList<UserModel>>() {}.type

fun generateRandomIds(): Long {
  return Random().nextLong()
}

class UserJSONStore : UserStore, AnkoLogger {

  val context: Context
  var users = mutableListOf<UserModel>()

  constructor (context: Context) {
    this.context = context
    if (exists(context, JSON_FILEs)) {
      deserialize()
    }
  }

  override fun findAll(): MutableList<UserModel> {
    return users
  }

  override fun create(user: UserModel) {
    user.idUser = generateRandomIds()
    users.add(user)
    serialize()
  }


  override fun update(user: UserModel) {
    var foundUser: UserModel? = users.find { p -> p.idUser == user.idUser }
    if (foundUser != null) {
      foundUser.name = user.name
      foundUser.email = user.email
      foundUser.password = user.password

      serialize()
    }
  }

  override fun findByEmail(email: String): Boolean {
    var foundUser: UserModel? = users.find { h -> h.email == email }
    return if (foundUser != null) {
      true
    } else {
      return false
    }
  }

  override fun authenticate(email: String, password: String): UserModel? {
    var foundUser: UserModel? = users.find { h -> h.email == email }

    if (foundUser != null) {
      if (foundUser.password == password) {
        return foundUser
      } else {
        return null
      }
    } else {
      return null
    }
  }

  private fun serialize() {
    val jsonString = gsonBuilders.toJson(users, listTypes)
    write(context, JSON_FILEs, jsonString)
  }


  private fun deserialize() {
    val jsonString = read(context, JSON_FILEs)
    users = Gson().fromJson(jsonString, listTypes)
  }

  override fun delete(user: UserModel) {
    users.remove(user)
    serialize()
  }
}