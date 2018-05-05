package weather.com.weatherapp.Info

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_weather.*
import weather.com.weatherapp.R
import weather.com.weatherapp.data.WeatherMapApiResponse
import weather.com.weatherapp.network.NetworkManager


class WeatherActivity : AppCompatActivity(), WeatherView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        val weatherPresenter = WeatherPresenter(this, NetworkManager.provideWeatherService())
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateWeatherInfo(response: WeatherMapApiResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}