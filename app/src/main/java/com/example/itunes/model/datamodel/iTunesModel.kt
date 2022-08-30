package com.example.itunes.model.datamodel

import com.google.gson.annotations.SerializedName

data class ItunesModel (
    @SerializedName("artistName") val artistName: String,
    @SerializedName("collectionName") val name: String,
    @SerializedName("collectionPrice") val price: String,
    @SerializedName("releaseDate") val date: String,
    @SerializedName("artworkUrl100") val url: String,
    @SerializedName("longDescription") val description: String,
    @SerializedName("primaryGenreName") val genre: String
    )

data class SectionModel(
    @SerializedName("results") val results: Array<ItunesModel>
)