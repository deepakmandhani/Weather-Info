package weather.com.weatherapp.data

import java.util.HashMap

class Coord {

    var lon: Double? = null
    var lat: Double? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
