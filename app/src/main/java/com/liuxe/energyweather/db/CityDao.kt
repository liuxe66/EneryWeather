package com.liuxe.energyweather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.liuxe.energyweather.bean.CityBean


@Dao
public interface CityDao {
    @Insert
    fun insertCitys(vararg city:CityBean)

    @Update
    fun updateCitys(vararg city:CityBean)

    @Query("SELECT * FROM CITY ORDER BY ID DESC")
    fun queryAllCity()

    @Query("SELECT * FROM CITY ORDER BY CITYNAME WHERE CITYNAME LIKE '%' + :CITYNAME" + '%')
    fun queryCity(cityName:String)
}