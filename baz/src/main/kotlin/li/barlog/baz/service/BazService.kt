package li.barlog.baz.service

import li.barlog.baz.client.BarClient
import li.barlog.baz.client.FooClient
import li.barlog.model.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Random
import java.util.UUID

@Service
open class BazService @Autowired constructor(val fooClient: FooClient,
											 val barClient: BarClient
) {
	companion object {
		private val log = LoggerFactory.getLogger(BazService::class.java)
	}

	@Scheduled(fixedRate = 2000)
	open fun request() {
		log.info("remote request")
		val pause = Random().nextInt(5000).toLong()
		Thread.sleep(pause)
		getRemoteMessage()
	}

	open fun createMessage() = run {
		val pause = Random().nextInt(5000).toLong()
		log.info("create local message with pause: $pause ms");
		Thread.sleep(pause);
		Message(LocalDateTime.now(), UUID.randomUUID().toString())
	}

	open fun getRemoteMessage() = run {
		when (Random().nextBoolean()) {
			true -> {
				log.info("get remote message from foo")
				fooClient.foo().body
			}
			else -> {
				log.info("get remote message from bar")
				barClient.bar().body
			}
		}
	}
}
