package jp.gihyo.wdpress.qiita.config

import jp.gihyo.wdpress.qiita.param.QiitaParam
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(QiitaParam::class)
class QiitaConfig {

    @Bean
    fun qiitaParam() = QiitaParam()
}