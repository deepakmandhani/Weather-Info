package weather.com.weatherapp.Info

import android.app.ProgressDialog
import android.graphics.drawable.Drawable
import android.net.MailTo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.content_weather.*
import weather.com.weatherapp.R
import weather.com.weatherapp.data.WeatherMapApiResponse
import weather.com.weatherapp.network.NetworkManager
import java.text.DateFormat
import java.util.*


class WeatherActivity : AppCompatActivity(), WeatherView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)

        val weatherPresenter = WeatherPresenter(this, NetworkManager.provideWeatherService())

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            weatherPresenter.getWeatherByCity("Bangalore,in")
        }

        weatherPresenter.getWeatherByCity("Bangalore,in")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_weather, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoader() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressbar.visibility = View.GONE
    }

    override fun updateWeatherInfo(response: WeatherMapApiResponse) {

        response.apply {
            city.setText(name?.toUpperCase() +
                    ", " + sys?.country)
            temperature.setText(String.format("%.2f", main?.temp) + " ℃")
            details.setText(weather?.get(0)?.description?.toUpperCase() +
                            "\n" + "Humidity: " + main?.humidity + "%" +
                            "\n" + "Pressure: " + main?.pressure + " hPa")

            val df = DateFormat.getDateTimeInstance()
            val updatedOn = df.format(Date(1000L* dt!!))
            last_updated.setText("Last temp update: " + updatedOn)

            setWeatherIcon(weather?.get(0)?.id!!,
                    sys?.sunrise!! * 1000L,
                    sys?.sunset!! * 1000L)
        }

    }

    private fun setWeatherIcon(actualId: Int, sunrise: Long, sunset: Long) {
        val id = actualId / 100
        var icon: Drawable = getDrawable(R.mipmap.rainbow)
        if (actualId == 800) {
            val currentTime = Date().time
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = getDrawable(R.mipmap.sunny)
            } else {
                icon = getDrawable(R.mipmap.moon_star)
            }
        } else {
            when (id) {
                2 -> icon = getDrawable(R.mipmap.sky)
                3 -> icon = getDrawable(R.mipmap.moon_cloud_light)
                7 -> icon = getDrawable(R.mipmap.sun_behind_cloud)
                8 -> icon = getDrawable(R.mipmap.sky_day)
                6 -> icon = getDrawable(R.mipmap.rain_drizzle)
                5 -> icon = getDrawable(R.mipmap.weather_rain)
            }
        }
        weather_icon.setImageDrawable(icon)
    }

}