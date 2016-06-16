package li.barlog.bar.rest

import li.barlog.bar.service.BarService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.Random
import java.util.concurrent.Callable

@RestController
open class BarController @Autowired constructor(val barService: BarService) {
	companion object {
		private val log = LoggerFactory.getLogger(BarService::class.java)
	}

	@RequestMapping(
		"/bar",
		method = arrayOf(RequestMethod.GET),
		consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
		produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
	)
	open fun bar() = Callable {
		log.info("bar")

		val message = when (Random().nextBoolean()) {
			true -> barService.createMessage()
			else -> barService.getRemoteMessage()
		}

		ResponseEntity.ok(message)
	}
}
