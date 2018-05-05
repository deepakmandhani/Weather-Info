package weather.com.weatherapp.data

import java.util.HashMap

class WeatherMapApiResponse {

    var coord: Coord? = null
    var weather: List<Weather>? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int? = null
    var wind: Wind? = null
    var clouds: Clouds? = null
    var dt: Int? = null
    var sys: Sys? = null
    var id: Int? = null
    var name: String? = null
    var cod: Int? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
