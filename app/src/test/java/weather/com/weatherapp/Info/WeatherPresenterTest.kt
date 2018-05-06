package weather.com.weatherapp.Info

import com.nhaarman.mockito_kotlin.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import rx.Observable
import rx.schedulers.TestScheduler
import weather.com.weatherapp.data.WeatherMapApiResponse
import weather.com.weatherapp.network.WeatherService

/**
 * Created by deepak.mandhani on 06/05/18.
 */
class WeatherPresenterTest {

    private val response: WeatherMapApiResponse = WeatherMapApiResponse()
    lateinit var presenter: WeatherPresenter
    lateinit var view: WeatherView
    lateinit var service: WeatherService
    lateinit var scheduler: TestScheduler
    val city = "Bangalore,in"

    @Before
    fun initializations() {

        view = mock {  }
        service = mock { on { getWeatherInfoByCity(eq(city), any()) } doReturn Observable.just(response)}
        scheduler = TestScheduler()
        presenter = WeatherPresenter(view, service, scheduler, scheduler)
    }
    @Test
    fun getWeatherByCity() {
        //when
        presenter.getWeatherByCity(city)

        //then
        verify(view).showLoader()
        verify(service).getWeatherInfoByCity(eq(city), any())

        reset(view)
        scheduler.triggerActions()

        verify(view).hideLoader()
        verify(view).updateWeatherInfo(response)

        verify(view, times(0)).showLoader()
        verify(view, never()).showLoader()
    }

    @Test
    fun getView() {
    }

    @Test
    fun getService() {
    }

}