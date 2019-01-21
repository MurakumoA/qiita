package jp.gihyo.wdpress.qiita.service

import jp.gihyo.wdpress.qiita.dto.QiitaItem
import jp.gihyo.wdpress.qiita.repository.ItemRepository
import jp.gihyo.wdpress.qiita.repository.RestComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QiitaService {

    lateinit var restComponent: RestComponent
    lateinit var qiitaRepository: ItemRepository

    @Autowired
    fun QiitaService(restComponent: RestComponent, qiitaRepository: ItemRepository) {
        this.restComponent = restComponent
        this.qiitaRepository = qiitaRepository
    }

    @Transactional(readOnly = false)
    fun crawlItems() {
        val items = restComponent.getItems()
        if (items != null) {
            items.forEach {item ->
                if (qiitaRepository.find(item.id) != null) {
                    qiitaRepository.update(item.id, item)
                } else {
                    qiitaRepository.create(item)
                }
            }
            //qiitaRepository.saveAll(items)
        }
    }

    fun findAll() = qiitaRepository.findAll()
}