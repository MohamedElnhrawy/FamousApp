package com.example.famousapp.famous.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Known_for(
    @Expose
    @SerializedName("release_date")
    val release_date: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("vote_count")
    val vote_count: Int,
    @Expose
    @SerializedName("video")
    val video: Boolean,
    @Expose
    @SerializedName("media_type")
    val media_type: String,
    @Expose
    @SerializedName("vote_average")
    val vote_average: Double,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("genre_ids")
    val genre_ids: List<Integer>,
    @Expose
    @SerializedName("original_title")
    val original_title: String,
    @Expose
    @SerializedName("original_language")
    val original_language: String,
    @Expose
    @SerializedName("adult")
    val adult: Boolean,
    @Expose
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @Expose
    @SerializedName("overview")
    val overview: String,
    @Expose
    @SerializedName("poster_path")
    val poster_path: String
) : Parcelable