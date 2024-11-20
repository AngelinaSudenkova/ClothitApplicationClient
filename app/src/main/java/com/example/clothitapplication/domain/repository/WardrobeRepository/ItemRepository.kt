package com.example.clothitapplication.domain.repository.WardrobeRepository

import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItemsByCategory(category: String): Flow<List<WardrobeShortEntity>>
    suspend fun getItemById(id: Int): ItemEntity?
    suspend fun addItem(item: ItemEntity)
    suspend fun deleteItem(item: ItemEntity)
    suspend fun updateItem(item: ItemEntity)
}