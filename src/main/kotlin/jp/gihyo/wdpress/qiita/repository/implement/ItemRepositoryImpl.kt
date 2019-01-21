package jp.gihyo.wdpress.qiita.repository.implement

import jp.gihyo.wdpress.qiita.domain.Item
import jp.gihyo.wdpress.qiita.dto.QiitaItem
import jp.gihyo.wdpress.qiita.repository.ItemRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class ItemRepositoryImpl: ItemRepository {
    override fun create(item: QiitaItem): QiitaItem {
        Item.insert {
            it[id] = item.id
            it[title] = item.title
            it[url] = item.url
            it[createTime] = item.createTime
            it[updateTime] = item.updateTime
        }
        return item

    }
    override fun update(key: String, item: QiitaItem) {
        Item.update( { Item.id eq key } ) {
            it[id] = item.id
        }
    }
//    override fun saveAll(items: List<QiitaItem>) {
//        items.forEach { item ->
//            if (Item.select { Item.id eq item.id }.count() == 1) {
//                Item.update( { Item.id eq item.id } ) {
//                    toRow(item)
//                }
//            } else {
//                Item.insert {
//                    it[id] = item.id
//                    toRow(item)
//                }
//            }
//        }
//    }
    override fun findAll(): Iterable<QiitaItem> {
        return Item.selectAll().map { fromRow(it) }
    }
    override fun find(key: String): QiitaItem? {
        return if (Item.select { Item.id eq key }.count() == 1) {
            fromRow(Item.select { Item.id eq key }.first())
        } else {
            null
        }
    }
    override fun deleteAll(): Int {
        return Item.deleteAll()
    }
    override fun delete(key: String): Int {
        return Item.deleteWhere { Item.id eq key }
    }

    private fun toRow(i: QiitaItem): Item.(UpdateBuilder<*>) -> Unit = {
        it[title] = i.title
        it[url] = i.url
        it[createTime] = i.createTime
        it[updateTime] = i.updateTime
    }

    private fun fromRow(r: ResultRow) =
            QiitaItem(r[Item.id],
                    r[Item.title],
                    r[Item.url],
                    r[Item.createTime],
                    r[Item.updateTime])
}