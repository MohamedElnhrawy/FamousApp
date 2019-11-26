package com.example.famousapp.famous.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.famousapp.data.local.db.dao.PersonDao
import com.example.famousapp.famous.data.model.Person
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        Person::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun dummyDao(): PersonDao

}