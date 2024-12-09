package com.example.clothitapplication.data.repository


import com.example.clothitapplication.data.dto.OutfitDto
import com.example.clothitapplication.data.repository.local.ItemDao
import com.example.clothitapplication.data.repository.local.OutfitDao
import com.example.clothitapplication.di.IoDispatcher
import com.example.clothitapplication.domain.model.DataOrException
import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OutfitRepositoryImpl @Inject constructor(
    private val outfitDao: OutfitDao,
    private val itemDao: ItemDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : OutfitRepository {

    override suspend fun getOutfits(): Flow<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> {
        return flow {
            emit(DataOrException(data = emptyList(), loading = true))
            try {
                outfitDao.getOutfits().collect() { outfits ->
                    val mappedItems = if (outfits.isNotEmpty()) {
                        outfits.map { it.toWardrobeShortEntity() }
                    } else {
                        emptyList()
                    }
                    emit(DataOrException(data = mappedItems, loading = false))
                }
            } catch (e: Exception) {
                emit(DataOrException(data = emptyList(), loading = false, e = e))
            }
        }
    }

    override suspend fun getOutfitById(id: Int): OutfitEntity {
        return withContext(dispatcher) {
            val outfitDto = outfitDao.getOutfitById(id)
            val itemEntities = outfitDto.itemIds.mapNotNull { itemId ->
                itemDao.getItemById(itemId)?.toItemEntity()
            }
            outfitDto.toOutfitEntity(itemEntities)
        }
    }


    override suspend fun addOutfit(outfit: OutfitEntity) : Int {
        var id = 0
        withContext(dispatcher) {
           id = outfitDao.addOutfit(outfit.toOutfitDto()).toInt()
        }
        return id
    }

    override suspend fun deleteOutfit(outfitId: Int) {
        withContext(dispatcher) {
            val outfitDto = outfitDao.getOutfitById(outfitId)
            outfitDao.deleteOutfit(outfitDto)
        }
    }

    override suspend fun updateOutfit(outfit: OutfitEntity) {
        withContext(dispatcher) {
            outfitDao.updateOutfit(outfit.toOutfitDto())
        }
    }
}