package weather.com.weatherapp.Location

import weather.com.weatherapp.db.City
import weather.com.weatherapp.db.DatabaseService

/**
 * Created by deepak.mandhani on 07/05/18.
 */
open class WeatherLocationPresenter(val view: WeatherLocationView, val service: DatabaseService) {


    fun addCity(city: String?, fav: Boolean?) {
        val cityObj = City()
        cityObj.name = city
        cityObj.isfav = fav
        service.addNewCity(cityObj)
    }

    fun getAllCity(): ArrayList<City> = service.getAllCity()

    fun getFavCity(): ArrayList<City> = service.getFavCity()
}