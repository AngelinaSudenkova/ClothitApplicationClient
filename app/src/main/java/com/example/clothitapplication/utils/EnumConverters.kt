package com.example.clothitapplication.utils

import com.example.clothitapplication.domain.model.wardrobeModel.Season
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory

object EnumConverters {
    fun fromStringToWardrobeCategory(value: String): WardrobeCategory {
        return WardrobeCategory.valueOf(value)
    }

    fun fromWardrobeCategoryToString(category: WardrobeCategory): String {
        return category.name
    }

    fun fromStringToSeason(value: String): Season {
        return Season.valueOf(value)
    }

    fun fromSeasonToString(season: Season): String {
        return season.name
    }
}