package weather.com.weatherapp.Info

import weather.com.weatherapp.data.WeatherMapApiResponse

/**
 * Created by deepak.mandhani on 05/05/18.
 */
interface WeatherView {

    fun showLoader()
    fun hideLoader()
    fun updateWeatherInfo(response: WeatherMapApiResponse)

}
