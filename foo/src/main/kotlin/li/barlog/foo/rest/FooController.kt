package li.barlog.foo.rest

import li.barlog.foo.service.FooService
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
open class FooController @Autowired constructor(val fooService: FooService) {
	companion object {
		private val log = LoggerFactory.getLogger(FooService::class.java)
	}

	@RequestMapping(
		"/foo",
		method = arrayOf(RequestMethod.GET),
		consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
		produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
	)
	open fun foo() = Callable {
		log.info("foo")

		val message = when (Random().nextBoolean()) {
			true -> fooService.createMessage()
			false -> fooService.getRemoteMessage()
		}

		ResponseEntity.ok(message)
	}
}
