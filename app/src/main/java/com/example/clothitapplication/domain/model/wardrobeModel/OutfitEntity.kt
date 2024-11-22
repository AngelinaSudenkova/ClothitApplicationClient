package com.example.clothitapplication.domain.model.wardrobeModel

import android.content.ClipDescription
import com.example.clothitapplication.data.dto.OutfitDto

data class OutfitEntity(
    val id: Int = 0,
    val imgUrl: String,
    val season: Season,
    val name: String,
    val description: String,
    val items: List<ItemEntity?>,
    val timeCreation: String,
    val timeEdition: String
){
    fun toWardrobeShortEntity(): WardrobeShortEntity {
        return WardrobeShortEntity(
            id = id,
            imgUrl = imgUrl,
            category = WardrobeCategory.OUTFITS
        )
    }
    fun toOutfitDto(): OutfitDto {
        return OutfitDto(
            id = id,
            name = name,
            itemIds = items.map { it!!.id },
            season = season.name,
            description = description,
            timeCreation = timeCreation,
            timeEdition = timeEdition,
            imgUrl = imgUrl
        )
    }
}