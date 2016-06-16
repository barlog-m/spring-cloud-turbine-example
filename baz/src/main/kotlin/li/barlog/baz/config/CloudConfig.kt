package li.barlog.baz.config

import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Configuration

@EnableHystrix
@EnableFeignClients("li.barlog.baz.client")
@EnableDiscoveryClient
@Configuration
open class CloudConfig {
}
