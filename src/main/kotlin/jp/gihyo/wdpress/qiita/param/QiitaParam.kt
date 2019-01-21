package jp.gihyo.wdpress.qiita.param

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "qiita.param")
class QiitaParam {
    lateinit var url: String
    lateinit var limit: Integer
}