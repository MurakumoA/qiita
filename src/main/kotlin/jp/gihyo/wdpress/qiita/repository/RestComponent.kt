package jp.gihyo.wdpress.qiita.repository

import jp.gihyo.wdpress.qiita.dto.QiitaItem
import jp.gihyo.wdpress.qiita.param.QiitaParam
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Slf4j
@Component
class RestComponent {

    companion object {
        private val log = LoggerFactory.getLogger(RestComponent::class.java)
    }

    lateinit var restTemplate: RestTemplate
    lateinit var qiitaParam: QiitaParam

    @Autowired
    fun RestComponent(restTemplate: RestTemplate, qiitaParam: QiitaParam) {
        this.restTemplate = restTemplate
        this.qiitaParam = qiitaParam
    }

    private inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}

    fun getItems(): List<QiitaItem>? {
        val uri = convertToUri(qiitaParam)
        log.info("url=[{}]", uri.toString())
        val requestEntity = RequestEntity.get(uri).build()
        val responseEntity = restTemplate.exchange(requestEntity, typeRef<List<QiitaItem>>())
        return responseEntity.body
    }

    private fun convertToUri(qiitaParam: QiitaParam): URI {
        val url = qiitaParam.url
        val limit = qiitaParam.limit
        val builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("page", 1)
                .queryParam("per_page", limit)
        return builder.build().toUri()
    }
}