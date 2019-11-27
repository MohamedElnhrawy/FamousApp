package com.example.famousapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.famousapp.data.local.db.entity.PersonEntity


@Dao
interface PersonDao {

    @Query("SELECT * FROM person_entity")
    fun getAll(): List<PersonEntity>

    @Insert
    fun insert(entity: PersonEntity)

    @Delete
    fun delete(entity: PersonEntity)
}