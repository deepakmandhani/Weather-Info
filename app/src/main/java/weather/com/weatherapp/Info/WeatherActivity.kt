package weather.com.weatherapp.Info

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.content_weather.*
import weather.com.weatherapp.Location.WeatherLocationActivity
import weather.com.weatherapp.R
import weather.com.weatherapp.network.NetworkManager

const val REQUEST_LOCATION = 777
class WeatherActivity : AppCompatActivity(), WeatherView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)

        val weatherPresenter = WeatherPresenter(this, NetworkManager.provideWeatherService())

        fab.setOnClickListener { _ ->
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
            R.id.action_settings ->
                openWeatherLocationActivity()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openWeatherLocationActivity(): Boolean {
        startActivityForResult(WeatherLocationActivity.newActivityInstance(this), REQUEST_LOCATION)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showLoader() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressbar.visibility = View.GONE
    }

    override fun updateWeatherInfo(cityName: String, temprature: String, detail: String, currdate: String) {
        city.text = cityName
        temperature.text =temprature
        details.text = detail
        last_updated.text = currdate
        weatherinfonotpresent.visibility = View.GONE
        weatherinfopresent.visibility = View.VISIBLE
    }

    override fun updateWeatherInfoNotPresent() {
        weatherinfonotpresent.visibility = View.VISIBLE
        weatherinfopresent.visibility = View.GONE
    }

    override fun setWeatherIcon(id: Int) {
        var icon: Drawable = getDrawable(R.mipmap.rainbow)
        when (id) {
            1 -> icon = getDrawable(R.mipmap.sunny)
            4 -> icon = getDrawable(R.mipmap.moon_star)
            2 -> icon = getDrawable(R.mipmap.sky)
            3 -> icon = getDrawable(R.mipmap.moon_cloud_light)
            7 -> icon = getDrawable(R.mipmap.sun_behind_cloud)
            8 -> icon = getDrawable(R.mipmap.sky_day)
            6 -> icon = getDrawable(R.mipmap.rain_drizzle)
            5 -> icon = getDrawable(R.mipmap.weather_rain)
        }
        weather_icon.setImageDrawable(icon)
    }

}