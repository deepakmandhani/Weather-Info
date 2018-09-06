package weather.com.weatherapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by deepak.mandhani on 07/05/18.
 */
open class WeatherApplication: Application() {

    val DB_NAME = "Weather.Realm"
    val REALM_SCHEMA = 1

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder().name(DB_NAME).schemaVersion(REALM_SCHEMA.toLong()).deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}