package com.aridwiprayogo.springr2dbcdemokotlin

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringR2dbcDemoKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringR2dbcDemoKotlinApplication>(*args){
        webApplicationType = WebApplicationType.REACTIVE
    }
}
