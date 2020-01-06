package com.liuxe.energyweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.liuxe.energyweather.bean.City


@Database(entities = [City::class],version = 1,exportSchema = false)
public abstract class CityDataBase:RoomDatabase() {


}