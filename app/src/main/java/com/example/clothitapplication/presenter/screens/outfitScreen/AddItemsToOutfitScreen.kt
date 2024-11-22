package com.example.clothitapplication.presenter.screens.outfitScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.navigation.AuthorizedClothitScreens
import com.example.clothitapplication.presenter.components.CreateOutfitScreenHeader
import com.example.clothitapplication.presenter.components.SelectButton
import com.example.clothitapplication.presenter.components.SelectImagePlaceholder
import com.example.clothitapplication.presenter.screens.wardrobeScreen.WardrobeViewModel

@Composable
fun AddItemsToOutfitScreen(
    itemCategory: String,
    navController: NavController,
    wardrobeViewModel: WardrobeViewModel = hiltViewModel(),
    outfitViewModel: CreateOutfitViewModel
) {
    val itemsIds = outfitViewModel.selectedItems.value
    var selectedIds by remember { mutableStateOf(itemsIds) }

    val items = when (itemCategory) {
        "TOPS" -> wardrobeViewModel.topsState.value.data
        "BOTTOMS" -> wardrobeViewModel.bottomsState.value.data
        "SHOES" -> wardrobeViewModel.shoesState.value.data
        "ACCESSORIES" -> wardrobeViewModel.accessoriesState.value.data
        else -> emptyList()
    }

    AddItemsToOutfitScreenContent(
        items = items ?: emptyList(),
        itemsIds = selectedIds,
        onItemSelected = {
            selectedIds = it
            outfitViewModel.addItemsIds(selectedIds)
        },
        navController = navController
    )
}


@Composable
fun AddItemsToOutfitScreenContent(
    items: List<WardrobeShortEntity>,
    navController: NavController,
    itemsIds: List<Int>,
    onItemSelected: (List<Int>) -> Unit = {}
) {

    var itemIds by remember { mutableStateOf(itemsIds) }

    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CreateOutfitScreenHeader { navController.popBackStack() }
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                SelectImagePlaceholder(
                    imageUri = Uri.parse(item.imgUrl),
                    itemId = item.id,
                    itemIds = itemIds,
                    onItemSelected = { newItemIds ->
                        itemIds = newItemIds
                        onItemSelected(newItemIds)
                    }
                )
            }
        }
        SelectButton(
            onButtonClick = {
                onItemSelected(itemIds)
                navController.navigate(AuthorizedClothitScreens.CreateOutfitScreen.name)
            },
        )
    }
}
