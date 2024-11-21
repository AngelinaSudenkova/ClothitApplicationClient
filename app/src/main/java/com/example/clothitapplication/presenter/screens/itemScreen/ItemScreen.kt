package com.example.clothitapplication.presenter.screens.itemScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.util.TimeUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.clothitapplication.navigation.Graph
import com.example.clothitapplication.presenter.components.AddButton
import com.example.clothitapplication.presenter.components.CategoryDropDownMenu
import com.example.clothitapplication.presenter.components.DescriptionField
import com.example.clothitapplication.presenter.components.ImagePlaceholder
import com.example.clothitapplication.presenter.components.ItemScreenTopBar
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.utils.EnumConverters
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ItemScreen(navController: NavController, itemViewModel: ItemViewModel = hiltViewModel<ItemViewModel>() ) {
    val context = LocalContext.current
    var selectedCategory by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri: Uri? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemScreenTopBar(onBackArrowCLick = { navController.navigate(Graph.HOME) })
        CategoryDropDownMenu(
            onCategorySelected = { selectedCategory = it }
        )
        DescriptionField(
            onDescriptionChange = { description = it }
        )
        ImagePlaceholder(
            onImageSelected = { selectedUri -> imageUri = selectedUri }
        )
        AddButton(
            onButtonClick = {
                imageUri?.let {
                    val item = ItemEntity(
                        category = EnumConverters.fromStringToWardrobeCategory(selectedCategory),
                        description = description,
                        imgUrl = imageUri.toString(),
                        timeCreation = getCurrentTime()
                    )
                    itemViewModel.saveItem(context, item, it)
                    navController.navigate(Graph.HOME)
                }
            }
        )
    }
}


fun getCurrentTime(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return dateFormat.format(Date(currentTimeMillis))
}