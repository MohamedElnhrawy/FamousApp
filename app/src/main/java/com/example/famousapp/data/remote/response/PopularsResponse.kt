package com.example.famousapp.famous.data.remote.response

import com.example.famousapp.famous.data.model.Person
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularsResponse(
    @Expose
    @SerializedName("page")
    val page: Int,
    @Expose
    @SerializedName("total_results")
    val total_results: Int,
    @Expose
    @SerializedName("total_pages")
    val total_pages: Int,
    @Expose
    @SerializedName("results")
    val results: List<Person>
)