package com.example.famousapp.famous.data.remote.response

import android.os.Parcelable
import com.example.famousapp.famous.data.model.Profiles
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesResponse (
    @Expose
    @SerializedName("profiles")
    val profiles: List<Profiles>,
    @Expose
    @SerializedName("id")
    val id: Int
) : Parcelable