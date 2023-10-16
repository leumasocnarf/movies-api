package com.cinema.dao.entities

import org.jetbrains.exposed.sql.Table

internal object MovieEntity : Table() {
    val id = integer("movie_id").autoIncrement()
    val title = varchar("movie_title", 128)
    val description = text("movie_description")
    val duration = integer("movie_duration")
    val year = integer("movie_year")

    override val primaryKey = PrimaryKey(id)
}