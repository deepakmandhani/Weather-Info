package weather.com.weatherapp.Location

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_city_list_item.view.*
import weather.com.weatherapp.R
import weather.com.weatherapp.db.City
import java.util.ArrayList

/**
 * Created by deepak.mandhani on 07/05/18.
 */
open class CityListRecyclerViewAdapter(private val context: Context, private var list: ArrayList<City>): RecyclerView.Adapter<CityListRecyclerViewAdapter.CityViewHolder>() {

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityObj = list[position]

        holder.view.city_name.text = cityObj.name
        if (cityObj.isfav!!)
            holder.view.fav_icon.setImageDrawable(context.getDrawable(R.mipmap.favourite_selected))
        else
            holder.view.fav_icon.setImageDrawable(context.getDrawable(R.mipmap.favourite_deselected))

        holder.view.setOnClickListener(View.OnClickListener { _ ->
            (context as WeatherLocationActivity).sendCityToFetchWheather(cityObj.name)
        })

        holder.view.fav_icon.setOnClickListener(View.OnClickListener { _ ->
            if (cityObj.isfav!!)
                holder.view.fav_icon.setImageDrawable(context.getDrawable(R.mipmap.favourite_deselected))
            else
                holder.view.fav_icon.setImageDrawable(context.getDrawable(R.mipmap.favourite_selected))
            (context as WeatherLocationActivity).presenter.addCity(cityObj.name, cityObj.isfav?.not())
        })
    }

    override fun getItemCount(): Int = list.size

    fun addItems(citylist: ArrayList<City>) {
        if (!list.isEmpty()) {
            list.clear()
            list.addAll(citylist)
        }
        else {
            list = citylist
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CityViewHolder =
        CityViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_city_list_item, parent, false))

    open class CityViewHolder(public var view: View) : RecyclerView.ViewHolder(view) {
    }
}
