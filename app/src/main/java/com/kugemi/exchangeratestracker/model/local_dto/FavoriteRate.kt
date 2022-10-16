package com.kugemi.exchangeratestracker.model.local_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVORITES")
class FavoriteRate {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
}