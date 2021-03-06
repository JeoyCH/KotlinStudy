package lech.kotlinstudy.db

import lech.kotlinstudy.R.id.date
import lech.kotlinstudy.domain.ForecastDataSource
import lech.kotlinstudy.model.DayForecast

/**
 * Created by Android_61 on 2017/6/16.
 * Description
 * Others
 */
class ForecastDb(
        val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dateManager: DbDataMapper = DbDataMapper()):ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use { }

    val dailyRequest = "${DayForecastTable.CITY_ID}=?" + "AND ${DayForecastTable.DATE} >=?"

    val dailyForecast = select(DayForecastTable.NAME).whereSimle(dailyRequest, ZipCode.toString(), date.toString()).parseList { DayForecast(HashMap(it)) }

    val dailyRequest1 = "${DayForecastTable.CITY_ID}={id}" + "AND ${DayForecastTable.DATE}?={date}"
    val dailyForecast1 = select(DayForecastTable.NAME).where(dailyRequest1, "id" to zipCode, "date" to date).parseList { DayForecast(HashMap(it)) }


}