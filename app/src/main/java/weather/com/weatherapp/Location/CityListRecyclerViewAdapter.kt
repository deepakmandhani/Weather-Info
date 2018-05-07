package weather.com.weatherapp.Location

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_city_list_item.view.*
import weather.com.weatherapp.R
import weather.com.weatherapp.data.WeatherMapApiResponse

/**
 * Created by deepak.mandhani on 07/05/18.
 */
open class CityListRecyclerViewAdapter(private val context: Context, private val list: List<WeatherMapApiResponse>): RecyclerView.Adapter<CityListRecyclerViewAdapter.CityViewHolder>() {

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindData(list[position].name, context.getDrawable(R.mipmap.favourite_selected))

        holder.view.setOnClickListener(View.OnClickListener { _ ->
            //todo set selection
        })

        holder.view.fav_icon.setOnClickListener(View.OnClickListener { _ ->
            if (list.get(position).base.isNullOrEmpty())
                holder.view.fav_icon.setImageDrawable(context.getDrawable(R.mipmap.favourite_selected))
            else
                holder.view.fav_icon.setImageDrawable(context.getDrawable(R.mipmap.favourite_deselected))
        })

    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CityViewHolder =
        CityViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_city_list_item, parent, false))

    open class CityViewHolder(public var view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(name: String?, icon: Drawable) {
            view.city_name.text = name
            view.fav_icon.setImageDrawable(icon)
        }

    }
}
