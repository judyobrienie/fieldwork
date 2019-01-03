package org.wit.fieldwork.models.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wit.fieldwork.models.FieldworkModel


@Database(entities = arrayOf(FieldworkModel::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun fieldworkDao(): FieldworkDao
}