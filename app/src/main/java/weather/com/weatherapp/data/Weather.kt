package weather.com.weatherapp.data

import java.util.HashMap

class Weather {

    var id: Int? = null
    var main: String? = null
    var description: String? = null
    var icon: String? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
