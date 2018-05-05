package weather.com.weatherapp.data

import java.util.HashMap

import weather.com.weatherapp.network.NetworkManager
import weather.com.weatherapp.network.*

class Clouds {

    var all: Int? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties.put(name, value)
    }

}
