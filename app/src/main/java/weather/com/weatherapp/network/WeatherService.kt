package weather.com.weatherapp.network

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import weather.com.weatherapp.data.WeatherMapApiResponse

/**
 * Created by deepak.mandhani on 05/05/18.
 */
const val API_KEY: String = "b6907d289e10d714a6e88b30761fae22"
interface WeatherService {

    @GET("weather")
    fun getWeatherInfoByCity(@Query("q") query: String,
                             @Query("appid") appId: String = API_KEY): Observable<WeatherMapApiResponse>
}