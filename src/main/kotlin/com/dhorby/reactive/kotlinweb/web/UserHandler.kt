package com.dhorby.reactive.kotlinweb.web

import com.dhorby.reactive.kotlinweb.User
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.LocalDate

@Suppress("UNUSED_PARAMETER")
class UserHandler {

    private val users = Flux.just(
            User("Foo", "Foo", LocalDate.now().minusDays(1)),
            User("Bar", "Bar", LocalDate.now().minusDays(10)),
            User("Baz", "Baz", LocalDate.now().minusDays(100)))

    private val userStream = Flux
            .zip(Flux.interval(Duration.ofMillis(100)), users.repeat())
            .map { it.t2 }

    fun findAll(req: ServerRequest) =
            ServerResponse.ok().body(users)

    fun findAllView(req: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok().render("users", mapOf("users" to users.map { "${it.firstName} ${it.firstName}"}))

    fun stream(req: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok().bodyToServerSentEvents(userStream)

}

class UserDto(val firstName: String, val lastName: String, val birthDate: String)
