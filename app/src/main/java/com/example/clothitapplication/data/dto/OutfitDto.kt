package com.example.clothitapplication.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.utils.EnumConverters

@Entity(tableName = "outfits")
data class OutfitDto(
    @PrimaryKey val id: Int,
    val name: String,
    val itemIds: List<Int>,
    val season: String,
    val description: String,
    val timeCreation: String,
    val timeEdition: String
){
    fun toOutfitEntity(items: List<ItemEntity>): OutfitEntity {
        val itemEntities = items.filter { it.id in itemIds }
        return OutfitEntity(
            id = this.id,
            name = this.name,
            items = itemEntities,
            season = EnumConverters.fromStringToSeason(this.season),
            timeCreation = this.timeCreation,
            imgUrl =  this.itemIds[0].toString(),
            description = this.description,
            timeEdition = this.timeEdition,
        )
    }

fun toWardrobeShortEntity(): WardrobeShortEntity {
    return WardrobeShortEntity(
        id = this.id,
        category =  WardrobeCategory.OUTFITS,
        imgUrl = this.itemIds[0].toString()
    )
}
}