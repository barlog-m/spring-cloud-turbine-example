package li.barlog.baz.client

import li.barlog.model.Message
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("bar")
interface BarClient {
	@RequestMapping(
		"/bar",
		method = arrayOf(RequestMethod.GET),
		consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
		produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
	fun bar(): ResponseEntity<Message>
}
