package org.wit.fieldwork.models.room

import android.content.Context
import androidx.room.Room
import org.jetbrains.anko.coroutines.experimental.bg
import org.wit.fieldwork.models.FieldworkModel

import org.wit.fieldwork.models.FieldworkStore

class FieldworkStoreRoom(val context: Context) : FieldworkStore {

    var dao: FieldworkDao

    init {
        val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
                .fallbackToDestructiveMigration()
                .build()
        dao = database.fieldworkDao()
    }

    suspend override fun findAll(): List<FieldworkModel> {
        val deferredFieldworks = bg {
            dao.findAll()
        }
        val fieldworks = deferredFieldworks.await()
        return fieldworks
    }

    suspend override fun findById(id: Long): FieldworkModel? {
        val deferredFieldwork = bg {
            dao.findById(id)
        }
        val fieldwork = deferredFieldwork.await()
        return fieldwork
    }

    suspend override fun create(fieldwork: FieldworkModel) {
        bg {
            dao.create(fieldwork)
        }
    }

    suspend override fun update(fieldwork: FieldworkModel) {
        bg {
            dao.update(fieldwork)
        }
    }

    suspend override fun delete(fieldwork: FieldworkModel) {
        bg {
            dao.deleteFieldwork(fieldwork)
        }
    }

    override fun clear() {
    }
}