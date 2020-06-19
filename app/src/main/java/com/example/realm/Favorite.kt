package com.example.realm

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Favorite: RealmObject() {
    @PrimaryKey
    var id = System.currentTimeMillis()

    var memoId = 0L

    companion object {
        fun findAll(): List<Favorite> =
            Realm.getDefaultInstance().use { realm ->
                realm.where(Favorite::class.java)
                    .findAll()
                    .let {
                        realm.copyFromRealm(it)
                    }
            }
    }
}