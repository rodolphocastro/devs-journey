package br.dev.ardc.devjourneycore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevJourneyCoreApplication

fun main(args: Array<String>) {
    runApplication<DevJourneyCoreApplication>(*args)
}