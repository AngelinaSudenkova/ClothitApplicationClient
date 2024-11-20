package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import javax.inject.Inject

class AddItemUC @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(item: ItemEntity) = repository.addItem(item)
}