package weather.com.weatherapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by deepak.mandhani on 05/05/18.
 */

class NetworkManager {

    companion object {
        private var BASE_URL: String = "http://openweathermap.org/data/2.5/"

        private fun provieRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
        }

        fun provideWeatherService(): WeatherService {
            return provieRetrofit().create(WeatherService::class.java)
        }
    }
}