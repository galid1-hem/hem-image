package com.galid.uploader

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HemImageUploaderApplication: CommandLineRunner {
	@Autowired
	lateinit var testM: TestM

	override fun run(vararg args: String?) {
		testM.createItems()
	}
}

fun main(args: Array<String>) {
	runApplication<HemImageUploaderApplication>(*args)
}
