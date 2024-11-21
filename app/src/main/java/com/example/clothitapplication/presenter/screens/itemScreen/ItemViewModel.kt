package com.example.clothitapplication.presenter.screens.itemScreen

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.usecase.wardrobeUC.AddItemUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetItemByIdUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.UpdateItemUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val addItemUC: AddItemUC,
    private val updateItemUC: UpdateItemUC,
    private val getItemByIdUC: GetItemByIdUC

) : ViewModel() {

    val _itemEntityState = mutableStateOf<ItemEntity?>(null)
    val itemEntity = _itemEntityState

    fun saveItem(context: Context, item: ItemEntity, imageUri: Uri) {

        viewModelScope.launch(Dispatchers.IO) {
            val id = addItemUC(item)
            val photoFile = savePhotoToInternalStorage(context, id.toString(), imageUri)
            if (photoFile != null) {
                val newItem = item.copy(id = id, imgUrl = photoFile.absolutePath)
                updateItemUC(newItem)
            }
        }
    }

    private fun savePhotoToInternalStorage(context: Context, id: String, imageUri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val file = File(context.filesDir, "$id.jpg")
            val fos = FileOutputStream(file)
            inputStream?.copyTo(fos)
            fos.close()
            inputStream?.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getItemById(itemId: Int) {
        viewModelScope.launch {
           itemEntity.value =  getItemByIdUC(itemId)
        }
    }

    fun updateItem(item: ItemEntity) {
        viewModelScope.launch {
            updateItemUC(item)
        }
    }
}