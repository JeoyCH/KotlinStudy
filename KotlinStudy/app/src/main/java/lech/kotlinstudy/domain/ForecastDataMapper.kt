package lech.weatherapp.domain

import lech.weatherapp.data.Forecast
import lech.weatherapp.data.ForecastResult
import java.text.DateFormat
import java.util.*
import lech.weatherapp.domain.Forecast as ModelForecast
/**
 * Created by Android_61 on 2017/6/6.
 * Description
 * Others
 */
public class ForecastDataMapper{

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {


        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }


    private fun convertForecastListToDomain(list:List<Forecast>):List<ModelForecast>{
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast):ModelForecast{
        return ModelForecast(convertDate(forecast.dt),forecast.weather[0].description,forecast.temp.max.toInt()
                ,forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))

    }

    private fun  generateIconUrl(icon: String): String ="http://openweathermap.org/img/w/$icon.png"

    private fun convertDate(date:Long):String{
        val df=DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date*1000)
    }

}