package org.wit.fieldwork.models

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import com.google.firebase.database.*


class FieldworkFireStore(val context: Context) : FieldworkStore, AnkoLogger {

    val fieldworks = ArrayList<FieldworkModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference

    suspend override fun findAll(): List<FieldworkModel> {
        return fieldworks
    }

    suspend override fun findById(id: Long): FieldworkModel? {
        val foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.id == id }
        return foundFieldwork
    }

    suspend override fun create(fieldwork: FieldworkModel) {
        val key = db.child("users").child(userId).child("fieldworks").push().key
        fieldwork.fbId = key!!
        fieldworks.add(fieldwork)
        db.child("users").child(userId).child("fieldworks").child(key).setValue(fieldwork)
    }

    suspend override fun update(fieldwork: FieldworkModel) {
        var foundFieldwork: FieldworkModel? = fieldworks.find { p -> p.fbId == fieldwork.fbId }
        if (foundFieldwork != null) {
            foundFieldwork.title = fieldwork.title
            foundFieldwork.description = fieldwork.description
            foundFieldwork.image = fieldwork.image
            foundFieldwork.location = fieldwork.location
        }

        db.child("users").child(userId).child("fieldworks").child(fieldwork.fbId).setValue(fieldwork)
    }

    suspend override fun delete(fieldwork: FieldworkModel) {
        db.child("users").child(userId).child("fieldworks").child(fieldwork.fbId).removeValue()
        fieldworks.remove(fieldwork)
    }

    override fun clear() {
        fieldworks.clear()
    }

    fun fetchFieldworks(fieldworksReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNullTo(fieldworks) { it.getValue<FieldworkModel>(FieldworkModel::class.java) }
                fieldworksReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        fieldworks.clear()
        db.child("users").child(userId).child("fieldworks").addListenerForSingleValueEvent(valueEventListener)
    }
}