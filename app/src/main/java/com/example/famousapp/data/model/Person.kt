package com.example.famousapp.data.model

import android.os.Parcelable
import com.example.famousapp.famous.data.model.Known_for
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person (
    @Expose
    @SerializedName("popularity")
    val popularity: Double,
    @Expose
    @SerializedName("known_for_department")
    val known_for_department: String,
    @Expose
    @SerializedName("gender")
    val gender: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("profile_path")
    val profile_path: String,
    @Expose
    @SerializedName("adult")
    val adult: Boolean,
    @Expose
    @SerializedName("known_for")
    val known_for: List<Known_for>,
    @Expose
    @SerializedName("name")
    val name: String
) : Parcelable