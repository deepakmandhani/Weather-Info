package weather.com.weatherapp.data

import java.util.HashMap

class Wind {

    var speed: Double? = null
    var deg: Int? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
