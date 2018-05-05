package weather.com.weatherapp.Info

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import weather.com.weatherapp.data.WeatherMapApiResponse
import weather.com.weatherapp.network.NetworkManager
import weather.com.weatherapp.network.WeatherService

/**
 * Created by deepak.mandhani on 05/05/18.
 */
class WeatherPresenter(val view: WeatherView,
                       val service: WeatherService) {


    fun getWeatherByCity(city: String){

        view.showLoader()
        service.getWeatherInfoByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { weather: WeatherMapApiResponse ->
                    view.updateWeatherInfo(weather)
                    view.hideLoader()
                })
        )
    }



}