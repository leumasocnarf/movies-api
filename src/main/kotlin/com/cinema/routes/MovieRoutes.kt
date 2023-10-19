package com.cinema.routes

import com.cinema.dao.repositories.MovieRepository
import com.cinema.models.MovieDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.IllegalStateException

fun Route.moviesRouting(movieRepository: MovieRepository) {
    route("/movies") {
        get {
            call.respond(movieRepository.getMovies())
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("id must be a number.")
            when (val movie = movieRepository.getMovieById(id)) {
                null -> call.respond(HttpStatusCode.NotFound)
                else -> call.respond(movie)
            }
        }
        post {
            val dto = call.receive<MovieDto>()
            movieRepository.createMovie(dto)
            call.respond(HttpStatusCode.Created)
        }
        patch("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("id must be a number.")
            val dto = call.receive<MovieDto>()
            movieRepository.updateMovie(id, dto)
            call.respond(HttpStatusCode.OK)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("id must be a number.")
            movieRepository.deleteMovie(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}