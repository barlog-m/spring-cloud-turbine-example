package li.barlog.bar

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
open class App: CommandLineRunner {
	companion object {
		private val log = LoggerFactory.getLogger(App::class.java)
	}

	override fun run(vararg args: String) {
	}
}

fun main(vararg args: String) {
	SpringApplicationBuilder(App::class.java)
		.registerShutdownHook(true).run(*args)
}
