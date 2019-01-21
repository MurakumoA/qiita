package jp.gihyo.wdpress.qiita.web

import jp.gihyo.wdpress.qiita.service.QiitaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller

@Controller
class QiitaScheduleController {
    companion object {
        const val INTERVAL: Long = 900_000
    }

    @Autowired
    lateinit var qiitaService: QiitaService

    @Scheduled(fixedRate=INTERVAL)
    fun crawlQiitaItems() {
        qiitaService.crawlItems()
    }

}