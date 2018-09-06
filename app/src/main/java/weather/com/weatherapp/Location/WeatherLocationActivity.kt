package weather.com.weatherapp.Location

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import weather.com.weatherapp.R
import kotlinx.android.synthetic.main.activity_weather_location.*
import kotlinx.android.synthetic.main.fragment_weather_location.*
import weather.com.weatherapp.db.City
import weather.com.weatherapp.db.DatabaseService
import android.content.DialogInterface
import android.text.InputType
import android.support.v7.app.AlertDialog
import android.widget.EditText



const val WEATHER_CITY = "weather_city"
class WeatherLocationActivity : AppCompatActivity(), WeatherLocationView {

    val presenter = WeatherLocationPresenter(this, DatabaseService())

    companion object {
        fun newActivityInstance(context: Context): Intent = Intent(context, WeatherLocationActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_location)
        setSupportActionBar(toolbar)

        val fragmentList = java.util.ArrayList<Fragment>()
        fragmentList.add(CityHolderFragment.newInstance(0))
        fragmentList.add(CityHolderFragment.newInstance(1))
        container.adapter = SectionsPagerAdapter(supportFragmentManager, fragmentList)
        header.setupWithViewPager(container)

        fab.setOnClickListener { _ ->
            openInputNewCityDialog()
        }

    }

    private fun openInputNewCityDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add new city")
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ -> addNewCity(input.text.toString()) })
        builder.show()
    }

    private fun addNewCity(city: String) {

        //val fragment = supportFragmentManager.findFragmentById(R.id.container) as CityHolderFragment
        //val currFragment: CityHolderFragment = supportFragmentManager.findFragmentByTag(“android:switcher:” + header + “:” + container.getCurrentItem)
        //container.adapter.instantiateItem(container, container.currentItem)

        //val isFav: Boolean = "All".contentEquals(container.adapter.getPageTitle(container.currentItem)
        presenter.addCity(city, container.currentItem == 1)
        ((container.adapter as SectionsPagerAdapter).getItem(container.currentItem) as CityHolderFragment).updateCityListView()


        //(fragmentManager.findFragmentByTag("android:switcher:"+container+":"+container.currentItem) as CityHolderFragment).updateCityListView()
    }

    fun sendCityToFetchWheather(cityName: String?) {
        setResult(Activity.RESULT_OK, Intent().putExtra(WEATHER_CITY, cityName))
        finish()
    }

    inner class SectionsPagerAdapter(fm: FragmentManager, val fragmentList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment = fragmentList[position]

        override fun getCount(): Int =  fragmentList.size

        override fun getPageTitle(position: Int): CharSequence =  if(position == 0) "All" else "Favouritie"

    }

    class CityHolderFragment : Fragment() {

        private var adapter: CityListRecyclerViewAdapter? = null

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_weather_location, container, false)

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            cityrecyclerview.setHasFixedSize(true)
            cityrecyclerview.layoutManager = LinearLayoutManager(activity)
            adapter = CityListRecyclerViewAdapter(context, ArrayList<City>())
            cityrecyclerview.adapter = adapter
        }

        override fun onResume() {
            super.onResume()
            adapter?.addItems(getCityListAsPerTab())
        }

        private fun getCityListAsPerTab(): ArrayList<City> =
            if (arguments.getInt(ARG_SCREEN_NUMBER) == 0)
                (activity as WeatherLocationActivity).presenter.getAllCity()
            else
                (activity as WeatherLocationActivity).presenter.getFavCity()

        fun updateCityListView() {
            adapter?.addItems(getCityListAsPerTab())
        }

        companion object {
            private val ARG_SCREEN_NUMBER = "screen_number"
            fun newInstance(sectionNumber: Int): CityHolderFragment {
                val fragment = CityHolderFragment()
                val args = Bundle()
                args.putInt(ARG_SCREEN_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
