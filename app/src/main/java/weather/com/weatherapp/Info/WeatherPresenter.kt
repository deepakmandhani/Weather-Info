package weather.com.weatherapp.Info

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
import weather.com.weatherapp.data.WeatherMapApiResponse
import weather.com.weatherapp.network.WeatherService
import java.text.DateFormat
import java.util.*

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
                    onPositiveResponse(weatherMapApiResponse)
                }, Action1 { _ ->
                    view.hideLoader()
                    view.updateWeatherInfoNotPresent()
                }, Action0 {  })
    }

    private fun onPositiveResponse(response: WeatherMapApiResponse) {

        response.apply {
            val cityName = name?.toUpperCase() + ", " + sys?.country
            val temperature = String.format("%.2f", main?.temp) + " ℃"
            val detail = weather?.get(0)?.description?.toUpperCase() +
                    "\n" + "Humidity: " + main?.humidity + "%" +
                    "\n" + "Pressure: " + main?.pressure + " hPa"
            val dated = "Last temp update: " + DateFormat.getDateTimeInstance().format(Date(1000L* dt!!))

            view.updateWeatherInfo(cityName, temperature, detail, dated)

            var id = weather?.get(0)?.id!! / 100
            if (id == 800) {
                val currentTime = Date().time
                if (currentTime in sys?.sunrise!! * 1000L..sys?.sunset!! * 1000L) {
                    id = 1
                } else {
                    id = 4
                }
            }
            view.setWeatherIcon(id)
        }
    }

}