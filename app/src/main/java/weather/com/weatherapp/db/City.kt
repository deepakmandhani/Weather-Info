package weather.com.weatherapp.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.parceler.Parcel

/**
 * Created by deepak.mandhani on 07/05/18.
 */

@Parcel
open class City : RealmObject() {

    @PrimaryKey
    var name: String? = null

    var isfav: Boolean? = false


}
