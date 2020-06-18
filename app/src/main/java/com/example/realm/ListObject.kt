package com.example.realm

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class ListObject: RealmObject() {
    @PrimaryKey
    var id : Long = System.currentTimeMillis()
    var title = ""

    companion object {
        fun findAll(): List<ListObject> =
            Realm.getDefaultInstance().use { realm ->
                realm.where(ListObject::class.java)
                    .findAll()
                    .let {
                        realm.copyFromRealm(it)
                    }
            }

        fun delete(data: ListObject) {
            Realm.getDefaultInstance().use {
                it.where(ListObject::class.java)
                    .equalTo(ListObject::id.name, data.id)
                    .findFirst()?.also { listObject ->
                        it.executeTransaction {
                            listObject.deleteFromRealm()
                        }
                    }
            }
        }
    }
}