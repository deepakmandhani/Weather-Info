package weather.com.weatherapp.Info

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.functions.Action2
import rx.schedulers.Schedulers
import weather.com.weatherapp.data.WeatherMapApiResponse
import weather.com.weatherapp.network.NetworkManager
import weather.com.weatherapp.network.WeatherService

/**
 * Created by deepak.mandhani on 05/05/18.
 */
class WeatherPresenter(val view: WeatherView,
                       val service: WeatherService,
                       val processScheduler: Scheduler = Schedulers.io(),
                       val androidScheduler: Scheduler = AndroidSchedulers.mainThread()) {


    fun getWeatherByCity(city: String){
        view.showLoader()
        service.getWeatherInfoByCity(city)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(
                        Action1 { weatherMapApiResponse ->
                    view.hideLoader()
                    view.updateWeatherInfo(weatherMapApiResponse)
                }, Action1 { throwable ->  throwable.printStackTrace()
                }, Action0 {  })
    }
}