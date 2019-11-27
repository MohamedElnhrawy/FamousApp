package com.example.famousapp.data.local.db.dao

import androidx.room.*
import com.example.famousapp.data.local.db.entity.PersonEntity


@Dao
interface PersonDao {

    @Query("SELECT * FROM person_entity")
    fun getAll(): List<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<PersonEntity>)

    @Insert
    fun insert(entity: PersonEntity)

    @Delete
    fun delete(entity: PersonEntity)
}