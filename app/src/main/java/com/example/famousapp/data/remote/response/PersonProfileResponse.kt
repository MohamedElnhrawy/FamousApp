package com.example.famousapp.famous.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonProfileResponse(
    @Expose
    @SerializedName("birthday")
    val birthday: String,
    @Expose
    @SerializedName("known_for_department")
    val known_for_department: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("also_known_as")
    val also_known_as: List<String>,
    @Expose
    @SerializedName("gender")
    val gender: Int,
    @Expose
    @SerializedName("biography")
    val biography: String,
    @Expose
    @SerializedName("popularity")
    val popularity: Double,
    @Expose
    @SerializedName("place_of_birth")
    val place_of_birth: String,
    @Expose
    @SerializedName("profile_path")
    val profile_path: String,
    @Expose
    @SerializedName("adult")
    val adult: Boolean,
    @Expose
    @SerializedName("imdb_id")
    val imdb_id: String
) : Parcelable