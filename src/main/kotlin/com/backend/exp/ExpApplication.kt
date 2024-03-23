package com.backend.exp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExpApplication

fun main(args: Array<String>) {
    runApplication<ExpApplication>(*args)
}
