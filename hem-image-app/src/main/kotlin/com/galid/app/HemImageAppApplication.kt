package com.galid.app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HemImageAppApplication

fun main(args: Array<String>) {
	runApplication<HemImageAppApplication>(*args)
}
