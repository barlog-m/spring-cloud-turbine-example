package li.barlog.model

import org.junit.Test
import org.junit.Assert.assertEquals
import java.time.LocalDateTime
import java.util.UUID

class MessageTest {
	@Test
	fun message() {
		val data = UUID.randomUUID().toString()
		val time = LocalDateTime.now()

		val message = Message(time, data)
		assertEquals(time, message.created)
		assertEquals(data, message.value)

		print(message)
	}
}
