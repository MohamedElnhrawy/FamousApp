package com.example.famousapp.famous.data.repository

import com.example.famousapp.famous.data.local.db.DatabaseService
import com.example.famousapp.famous.data.remote.NetworkService
import com.example.famousapp.famous.data.remote.response.ImagesResponse
import com.example.famousapp.famous.data.remote.response.PersonProfileResponse
import com.example.famousapp.famous.data.remote.response.PopularsResponse
import io.reactivex.Single
import javax.inject.Inject

class PopularRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService

) {

    fun fetchPopular(language : String , page: Int): Single<PopularsResponse> =
        networkService.getPopularPeople(language,page).map { it }

    fun fetchPersonProfile(person_id : Int , language : String): Single<PersonProfileResponse> =
            networkService.getPersonProfile(person_id,language).map { it }

    fun fetchPersonImages(person_id : Int) : Single<ImagesResponse> =
        networkService.getPersonImages(person_id).map { it }

}