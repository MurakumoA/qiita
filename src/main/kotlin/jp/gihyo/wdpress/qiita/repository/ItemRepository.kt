package jp.gihyo.wdpress.qiita.repository

import jp.gihyo.wdpress.qiita.dto.QiitaItem

interface ItemRepository: CrudRepository<QiitaItem, String> {
    //fun saveAll(items: List<QiitaItem>)
}