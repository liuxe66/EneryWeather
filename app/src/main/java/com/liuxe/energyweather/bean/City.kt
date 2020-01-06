package com.liuxe.energyweather.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City")
class City {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = 0

    @ColumnInfo(name = "cityId")
    var cityid:String? = null

    @ColumnInfo(name = "cityName")
    var city:String? = null

    @ColumnInfo(name = "isDot")
    var isDot:Boolean? = false

}