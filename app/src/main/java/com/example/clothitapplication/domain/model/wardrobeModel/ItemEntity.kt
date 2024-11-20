package com.example.clothitapplication.domain.model.wardrobeModel

import com.example.clothitapplication.data.dto.ItemDto

data class ItemEntity(
    val id: Int,
    val imgUrl: String,
    val category: WardrobeCategory,
    val description: String,
    val timeCreation: String
){
    fun toWardrobeShortEntity(): WardrobeShortEntity {
        return WardrobeShortEntity(
            id = id,
            imgUrl = imgUrl,
            category = category
        )
    }

    fun toItemDto(): ItemDto {
        return ItemDto(
            id = id,
            name = imgUrl,
            category = category.name,
            description = description,
            timeCreation = timeCreation,
            imagePath = imgUrl
        )
    }
}
