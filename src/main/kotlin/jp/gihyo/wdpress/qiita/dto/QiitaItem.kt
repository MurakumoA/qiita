package jp.gihyo.wdpress.qiita.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class QiitaItem(
        val id: String,
        val title: String,
        val url: String,
        @JsonProperty("created_at")
        val createTime: String,
        @JsonProperty("updated_at")
        val updateTime: String
)