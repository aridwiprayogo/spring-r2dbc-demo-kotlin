package com.aridwiprayogo.springr2dbcdemokotlin.route

import com.aridwiprayogo.springr2dbcdemokotlin.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Component
class Route(val handler: UserHandler) {
    @Bean
    fun userRoute(): RouterFunction<ServerResponse> = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "api".nest {
                POST("/", handler::saveUser)
                POST("/login", handler::getUserByName)
                GET("/", handler::getAllUser)
                PUT("/{id}", handler::updateUser)
                DELETE("/{id}", handler::deleteUser)
            }
        }
        resources("/**", ClassPathResource("static/"))
    }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.csrf().disable()
            .build()
}