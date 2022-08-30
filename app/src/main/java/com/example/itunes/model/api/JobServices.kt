package com.example.itunes.model.api

import com.example.itunes.interfaces.Constants
import com.example.itunes.model.datamodel.SectionModel
import com.example.itunes.view.getLimit
import com.example.itunes.view.getTerm
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobServices {
    @GET(Constants.MOVIE)
    suspend fun getMovie(
        @Query("limit") limit: Int = getLimit(),
        @Query("term") term: String = getTerm()): Response<SectionModel>

    @GET(Constants.MUSIC)
    suspend fun getMusic(
        @Query("limit") limit: Int = getLimit(),
        @Query("term") term: String = getTerm()): Response<SectionModel>

    @GET(Constants.PODCAST)
    suspend fun getPodcast(
        @Query("limit") limit: Int = getLimit(),
        @Query("term") term: String = getTerm()): Response<SectionModel>

    @GET(Constants.BOOK)
    suspend fun getBook(
        @Query("limit") limit: Int = getLimit(),
        @Query("term") term: String = getTerm()): Response<SectionModel>

    @GET(Constants.ALL)
    suspend fun getAll(
        @Query("limit") limit: Int = getLimit(),
        @Query("term") term: String = getTerm()): Response<SectionModel>
}