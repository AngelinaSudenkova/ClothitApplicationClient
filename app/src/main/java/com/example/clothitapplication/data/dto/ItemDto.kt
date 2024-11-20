package com.example.clothitapplication.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.utils.EnumConverters

@Entity(tableName = "items")
data class ItemDto(
    @PrimaryKey val id: Int,
    val name: String,
    val category: String,
    val description: String,
    val timeCreation: String,
    val imagePath: String
) {
    fun toWardrobeShortEntity(): WardrobeShortEntity {
        return WardrobeShortEntity(
            id = this.id,
            category = EnumConverters.fromStringToWardrobeCategory(this.category),
            imgUrl = this.imagePath
        )
    }

    fun toItemEntity(): ItemEntity {
        return ItemEntity(
            id = this.id,
            category = EnumConverters.fromStringToWardrobeCategory(this.category),
            description = this.description,
            timeCreation = this.timeCreation,
            imgUrl = this.imagePath
        )
    }
}