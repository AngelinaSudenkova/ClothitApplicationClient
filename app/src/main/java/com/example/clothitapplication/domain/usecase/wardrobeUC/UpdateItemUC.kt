package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import javax.inject.Inject

class UpdateItemUC @Inject constructor(
    private val repository: ItemRepository
) {
   suspend fun invoke(itemEntity: ItemEntity) = repository.updateItem(itemEntity)
}