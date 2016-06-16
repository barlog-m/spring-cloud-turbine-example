package li.barlog.bar

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import li.barlog.foo.App
import li.barlog.model.Message
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import java.net.URI

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(App::class))
@WebAppConfiguration
@IntegrationTest("server.port=0")
class IntegrationTest {
	companion object {
		private val log = LoggerFactory.getLogger(IntegrationTest::class.java)
		private val mapper = ObjectMapper()
	}

	@Value("\${local.server.port}")
	val port: Int = 0

	val url by lazy { "http://localhost:$port" }

	val template = TestRestTemplate()
	val headers = HttpHeaders()

	@Before
	fun init() {
		mapper.registerModule(JavaTimeModule())
		mapper.registerModule(KotlinModule())

		headers.contentType = MediaType.APPLICATION_JSON_UTF8
	}

	@Test
	fun get() {
		log.info("$url/bar")
		val req = RequestEntity<Void>(headers, HttpMethod.GET, URI("$url/bar"))
		val res = template.exchange(req, String::class.java)
		Assert.assertEquals(res.statusCode, HttpStatus.OK)

		val message = mapper.readValue<Message>(res.body, Message::class.java)
		log.info(message.toString())
	}
}
