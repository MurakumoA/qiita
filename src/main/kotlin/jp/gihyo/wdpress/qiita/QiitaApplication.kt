package jp.gihyo.wdpress.qiita

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class QiitaApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<QiitaApplication>(*args)
		}
	}
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	fun dataSource() = DataSourceBuilder.create().build()
}

