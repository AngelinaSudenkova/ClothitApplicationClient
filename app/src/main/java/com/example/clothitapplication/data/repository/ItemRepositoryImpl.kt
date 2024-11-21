package com.example.clothitapplication.data.repository


import com.example.clothitapplication.data.dto.ItemDto
import com.example.clothitapplication.data.repository.local.ItemDao
import com.example.clothitapplication.di.IoDispatcher
import com.example.clothitapplication.domain.model.DataOrException
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ItemRepository {
    override suspend fun getItemsByCategory(category: String): Flow<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> {
        return flow {
            emit(DataOrException(data = emptyList(), loading = true))
            try {
                itemDao.getItemsByCategory(category).collect { items ->
                    val mappedItems = if (items.isNotEmpty()) {
                        items.map { it.toWardrobeShortEntity() }
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

        override suspend fun getItemById(id: Int): ItemEntity? {
            return withContext(dispatcher) {
                itemDao.getItemById(id)?.toItemEntity()
            }
        }

        override suspend fun addItem(item: ItemEntity): Int {
            var id = 0
            withContext(dispatcher) {
                id =  itemDao.addItem(item.toItemDto()).toInt()
            }
            return id
        }

        override suspend fun deleteItem(item: ItemEntity) {
            withContext(dispatcher) {
                itemDao.deleteItem(item.toItemDto())
            }
        }

        override suspend fun updateItem(item: ItemEntity) {
            withContext(dispatcher) {
                itemDao.updateItem(item.toItemDto())
            }
        }
    }