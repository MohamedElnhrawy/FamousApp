package com.example.famousapp.famous.data.remote

import com.example.famousapp.famous.data.remote.Endpoints.IMAGES
import com.example.famousapp.famous.data.remote.Endpoints.POPULAR
import com.example.famousapp.famous.data.remote.Endpoints.PROFILE
import com.example.famousapp.famous.data.remote.Networking.PARAM_API_KEY
import com.example.famousapp.famous.data.remote.Networking.PARAM_LANGUAGE
import com.example.famousapp.famous.data.remote.Networking.PARAM_PAGE
import com.example.famousapp.famous.data.remote.Networking.PERSON_ID_PARAM
import com.example.famousapp.famous.data.remote.response.ImagesResponse
import com.example.famousapp.famous.data.remote.response.PersonProfileResponse
import com.example.famousapp.famous.data.remote.response.PopularsResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(POPULAR)
    fun getPopularPeople(
        @Query(PARAM_LANGUAGE) language: String,  // pass using UserRepository
        @Query(PARAM_PAGE) pageNumber: Int,   // pass using UserRepository,
        @Query(PARAM_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PopularsResponse>

    @GET(PROFILE)
    fun getPersonProfile(
        @Path(PERSON_ID_PARAM) person_id : Int ,
        @Query(PARAM_LANGUAGE) language: String,  // pass using UserRepository
        @Query(PARAM_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PersonProfileResponse>


    @GET(IMAGES)
    fun getPersonImages(
        @Path(PERSON_ID_PARAM) person_id : Int ,
        @Query(PARAM_API_KEY) apiKey: String = Networking.API_KEY
    ) : Single<ImagesResponse>
}