package weather.com.weatherapp.data

import java.util.HashMap

class Sys {

    var type: Int? = null
    var id: Int? = null
    var message: Double? = null
    var country: String? = null
    var sunrise: Int? = null
    var sunset: Int? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
