package com.cinema.models

import kotlinx.serialization.Serializable

@Serializable
internal data class Movie(
    private val id: Int,
    private val title: String,
    private val description: String,
    private val duration: Int,
    private val year: Int
)

@Serializable
data class MovieDto(
    val title: String,
    val description: String,
    val duration: Int,
    val year: Int
)

