package com.cinema.dao.repositories

import com.cinema.dao.DatabaseFactory.dbQuery
import com.cinema.dao.entities.MovieEntity
import com.cinema.models.Movie
import com.cinema.models.MovieDto
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class MovieRepository {
    private fun rowToMovie(row: ResultRow) = Movie(
        id = row[MovieEntity.id],
        title = row[MovieEntity.title],
        description = row[MovieEntity.description],
        duration = row[MovieEntity.duration],
        year = row[MovieEntity.year]
    )

    internal suspend fun getMovies(): List<Movie> = dbQuery {
        MovieEntity.selectAll().map { rowToMovie(it) }
    }

    internal suspend fun getMovieById(id: Int): Movie? = dbQuery {
        MovieEntity.select(MovieEntity.id eq id).map { rowToMovie(it) }.singleOrNull()
    }

    internal suspend fun createMovie(
        movieDto: MovieDto
    ): Movie? = dbQuery {
        val statement = MovieEntity.insert {
            it[title] = movieDto.title
            it[description] = movieDto.description
            it[duration] = movieDto.duration
            it[year] = movieDto.year
        }

        statement.resultedValues?.singleOrNull()?.let { rowToMovie(it) }
    }

    internal suspend fun updateMovie(
        id: Int,
        movieDto: MovieDto
    ): Boolean = dbQuery {
        MovieEntity.update({ MovieEntity.id eq id }) {
            it[title] = movieDto.title
            it[description] = movieDto.description
            it[duration] = movieDto.duration
            it[year] = movieDto.year
        } > 0
    }

    internal suspend fun deleteMovie(id: Int): Boolean = dbQuery {
        MovieEntity.deleteWhere { MovieEntity.id eq id } > 0
    }

}