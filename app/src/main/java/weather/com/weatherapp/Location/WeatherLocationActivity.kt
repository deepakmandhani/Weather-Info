package weather.com.weatherapp.Location

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

class WeatherLocationActivity : AppCompatActivity() {

    companion object {
        fun newActivityInstance(context: Context): Intent = Intent(context, WeatherLocationActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_location)
        setSupportActionBar(toolbar)

        container.adapter = SectionsPagerAdapter(supportFragmentManager)
        header.setupWithViewPager(container)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return if(position == 0) CityHolderFragment.newInstance(position)
            else CityHolderFragment.newInstance( 1)
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return if(position == 0) "All" else "Favouritie"
        }
    }

    class CityHolderFragment : Fragment() {

        private var adapter: CityListRecyclerViewAdapter? = null

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_weather_location, container, false)
            //rootView.section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SCREEN_NUMBER))
            return rootView
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            cityrecyclerview.setHasFixedSize(true)
            cityrecyclerview.layoutManager = LinearLayoutManager(activity)
            adapter = CityListRecyclerViewAdapter(context, listOf())
            cityrecyclerview.adapter = adapter
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
