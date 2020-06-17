package com.example.realm

import android.app.Application
import io.realm.Realm

class RealmApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Realmの初期化
        Realm.init(this)
//        val config = RealmConfiguration.Builder()
////            .deleteRealmIfMigrationNeeded() // FIXME:この処理はMigrationをやる際に消す場合に書く処理です、Migrationに関しては次回やりますね。
//            .build()
//        Realm.setDefaultConfiguration(config)

        // FIXME:stetho入れると確かにいろいろ楽になるのですが、メンテされてないので、今は使わないほうが良いと思います。

        // stetho-realmの初期化
//        Stetho.initialize(
//            Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                .build())
    }
}