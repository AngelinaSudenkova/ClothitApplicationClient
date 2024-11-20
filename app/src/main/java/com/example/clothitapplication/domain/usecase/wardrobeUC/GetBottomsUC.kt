package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import javax.inject.Inject

class GetBottomsUC @Inject constructor(
    private val repository: ItemRepository
){
    suspend operator fun invoke() = repository.getItemsByCategory("BOTTOMS")
}