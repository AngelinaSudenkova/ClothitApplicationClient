package com.example.clothitapplication.presenter.screens.itemScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.presenter.components.CategoryDropDownMenu
import com.example.clothitapplication.presenter.components.ItemScreenTopBar
import com.example.clothitapplication.utils.EnumConverters
import com.example.clothitapplication.presenter.components.DescriptionField
import com.example.clothitapplication.presenter.components.FilledImagePlaceholder
import com.example.clothitapplication.presenter.components.AddButton

@Composable
fun UpdateItemScreen(
    navController: NavController,
    itemId: Int,
    itemViewModel: ItemViewModel = hiltViewModel<ItemViewModel>()
) {
    val item = itemViewModel.itemEntity.value
    LaunchedEffect(Unit) {
        itemViewModel.getItemById(itemId)
    }
    item?.let {
        UpdateItemForm(
            item = it,
            onBackArrowClick = { navController.popBackStack() },
            onUpdateButtonClicked = { itemViewModel.updateItem(it) }
        )
    }

}

@Composable
fun UpdateItemForm(
    item: ItemEntity,
    onBackArrowClick: () -> Unit,
    onUpdateButtonClicked: (ItemEntity) -> Unit
) {
    var newItem = item.copy()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemScreenTopBar(title = "Update item", onBackArrowCLick = onBackArrowClick)
        CategoryDropDownMenu(
            oldCategory = item.category.toString(),
            onCategorySelected = {
                newItem = newItem.copy(category = EnumConverters.fromStringToWardrobeCategory(it))
            }
        )
        DescriptionField(
            oldDescription = item.description,
            onDescriptionChange = { newItem = newItem.copy(description = it) }
        )
        FilledImagePlaceholder(
            imageUri = Uri.parse(item.imgUrl),
            onImageSelected = { selectedUri ->
                newItem = newItem.copy(imgUrl = selectedUri.toString())
            }
        )

        AddButton(
            onButtonClick = {
                onUpdateButtonClicked(newItem)
                onBackArrowClick()
            }
        )
    }
}
