package com.example.famousapp.famous.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "person_entity")
@Parcelize
data class Person (
    var popularity: Double = 0.0,
    var known_for_department: String = "",
    var gender: Int = -1,
    @PrimaryKey
    var id: Int = -1,
    var profile_path: String = "",
    var adult: Boolean = false,
    var name: String = ""
) : Parcelable