package jp.gihyo.wdpress.qiita.domain

import org.jetbrains.exposed.sql.Table

object Item: Table(name = "item") {
    val id = varchar("id", 40).primaryKey()
    val title = varchar("title", 200)
    val url = varchar("url", 2100)
    val createTime = varchar("createTime", 100)
    val updateTime = varchar("updateTime", 100)
}