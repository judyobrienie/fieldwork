package org.wit.fieldwork.models.json

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import java.util.*
import android.content.Context
import com.google.gson.Gson
import org.wit.fieldwork.helpers.*
import org.wit.fieldwork.models.FieldworkModel
import org.wit.fieldwork.models.FieldworkStore


val JSON_FILE = "fieldworks.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<FieldworkModel>>() {}.type

fun generateRandomId(): Long {
  return Random().nextLong()
}

class FieldworkJSONStore : FieldworkStore, AnkoLogger {


  suspend override fun findById(id: Long): FieldworkModel {
    var foundFieldwork : FieldworkModel = fieldworks.find { p -> p.id == id }!!
    return foundFieldwork

  }

  val context: Context
  var fieldworks = mutableListOf<FieldworkModel>()

  constructor (context: Context) {
    this.context = context
    if (exists(context, JSON_FILE)) {
      deserialize()
    }
  }

  suspend override fun findAll(): MutableList<FieldworkModel> {
    return fieldworks
  }

  suspend override fun create(fieldwork: FieldworkModel) {
    fieldwork.id = generateRandomId()
    fieldworks.add(fieldwork)
    serialize()
  }



  suspend override fun update(fieldwork: FieldworkModel) {
    var foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.id == fieldwork.id }
    if (foundFieldwork != null) {
      foundFieldwork.title = fieldwork.title
      foundFieldwork.description = fieldwork.description
      foundFieldwork.image = fieldwork.image
      foundFieldwork.location = fieldwork.location
    //add to file
      serialize()
    }
  }

  private fun serialize() {
    val jsonString = gsonBuilder.toJson(fieldworks, listType)
    write(context, JSON_FILE, jsonString)
  }

  private fun deserialize() {
    val jsonString = read(context, JSON_FILE)
    fieldworks = Gson().fromJson(jsonString, listType)
  }

  suspend override fun delete(fieldwork: FieldworkModel) {
    fieldworks.remove(fieldwork)
    serialize()
  }

  override fun clear() {
    fieldworks.clear()
  }
}