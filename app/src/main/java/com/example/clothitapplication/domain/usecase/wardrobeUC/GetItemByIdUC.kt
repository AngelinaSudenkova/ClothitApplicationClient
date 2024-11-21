package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import javax.inject.Inject

class GetItemByIdUC @Inject constructor(
    private val repository: ItemRepository
){
    suspend operator fun invoke(itemId: Int) = repository.getItemById(itemId)
}