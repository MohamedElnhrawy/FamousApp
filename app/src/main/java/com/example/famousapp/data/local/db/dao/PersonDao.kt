package com.example.famousapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.famousapp.famous.data.model.Person


@Dao
interface PersonDao {

    @Query("SELECT * FROM person_entity")
    fun getAll(): List<Person>

    @Insert
    fun insert(entity: Person)

    @Delete
    fun delete(entity: Person)
}