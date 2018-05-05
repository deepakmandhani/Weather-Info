package weather.com.weatherapp.data

import java.util.HashMap

class Main {

    var temp: Double? = null
    var pressure: Int? = null
    var humidity: Int? = null
    var tempMin: Double? = null
    var tempMax: Double? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
