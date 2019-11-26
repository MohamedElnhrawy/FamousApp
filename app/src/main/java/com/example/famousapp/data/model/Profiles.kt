package com.example.famousapp.famous.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profiles(
    @Expose
    @SerializedName("width")
    val width: Int,
    @Expose
    @SerializedName("height")
    val height: Int,
    @Expose
    @SerializedName("vote_count")
    val vote_count: Int,
    @Expose
    @SerializedName("vote_average")
    val vote_average: Double,
    @Expose
    @SerializedName("file_path")
    val file_path: String,
    @Expose
    @SerializedName("aspect_ratio")
    val aspect_ratio: Double

) : Parcelable