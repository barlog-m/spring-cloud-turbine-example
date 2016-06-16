package li.barlog.baz.rest

import li.barlog.baz.service.BazService
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
open class BazController @Autowired constructor(val bazService: BazService) {
	companion object {
		private val log = LoggerFactory.getLogger(BazService::class.java)
	}

	@RequestMapping(
		"/baz",
		method = arrayOf(RequestMethod.GET),
		consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
		produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
	)
	open fun baz() = Callable {
		log.info("baz")

		val message = when (Random().nextBoolean()) {
			true -> bazService.createMessage()
			false -> bazService.getRemoteMessage()
		}

		ResponseEntity.ok(message)
	}
}
