package com.example.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class ListObject: RealmObject() {
    @PrimaryKey
    var id : Int? = null
    @Required
    var title = ""
}