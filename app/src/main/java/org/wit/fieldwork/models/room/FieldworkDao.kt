package org.wit.fieldwork.models.room

import org.wit.fieldwork.models.FieldworkModel
import androidx.room.*



@Dao
interface FieldworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(fieldwork: FieldworkModel)

    @Query("SELECT * FROM FieldworkModel")
    fun findAll(): List<FieldworkModel>

    @Query("select * from FieldworkModel where id = :id")
    fun findById(id: Long): FieldworkModel

    @Update
    fun update(fieldwork: FieldworkModel)

    @Delete
    fun deleteFieldwork(fieldwork: FieldworkModel)
}
