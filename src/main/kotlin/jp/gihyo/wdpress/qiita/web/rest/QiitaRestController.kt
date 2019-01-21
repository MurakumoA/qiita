package jp.gihyo.wdpress.qiita.web.rest

import jp.gihyo.wdpress.qiita.dto.QiitaItem
import jp.gihyo.wdpress.qiita.service.QiitaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class QiitaRestController {

    @Autowired
    lateinit var qiitaService: QiitaService

    @GetMapping("/items")
    fun getItems(): ResponseEntity<List<QiitaItem>> {
        val items = qiitaService.findAll()
        return ResponseEntity.ok().body(items.toList())
    }
}