package com.example.realm

import io.realm.Realm
import io.realm.RealmConfiguration

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()

        // Realmの初期化
        Realm.init(this)
        val config = RealmConfiguration.Builder()
//            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

        // stetho-realmの初期化
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build())
    }
}