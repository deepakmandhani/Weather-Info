package weather.com.weatherapp.db

import io.realm.Realm
import weather.com.weatherapp.Location.WeatherLocationActivity
import java.util.ArrayList

/**
 * Created by deepak.mandhani on 07/05/18.
 */
class DatabaseService(private val realm: Realm = Realm.getDefaultInstance()) {

    companion object {

        private lateinit var self: DatabaseService
        fun getInstance(): DatabaseService {
            //if (self == null)
            self = DatabaseService()
            return self
        }
    }

    fun getAllCity(): ArrayList<City> {
        val list = ArrayList<City>()
        realm.beginTransaction()
        val result = realm.where(City::class.java).findAll()
        list.addAll(realm.copyFromRealm(result))
        realm.commitTransaction()
        return list
    }

    fun getFavCity(): ArrayList<City> {
        val list = ArrayList<City>()
        realm.beginTransaction()
        val result = realm.where(City::class.java).equalTo("isfav", true).findAll()
        list.addAll(realm.copyFromRealm(result))
        realm.commitTransaction()
        return list
    }

    fun addNewCity(city: City) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(city)
        realm.commitTransaction()
    }
}