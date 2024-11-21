package com.example.clothitapplication.presenter.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.example.clothitapplication.R
import androidx.compose.material3.Icon
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.clothitapplication.navigation.AuthorizedClothitScreens
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun WardrobeTopBar(
    title: String = stringResource(R.string.your_wardrobe),
    onItemAddClick: () -> Unit = {},
    onCreateOutfitClick: () -> Unit = {}
) {
    val expanded = remember { mutableStateOf(false) }

    val hazeState = remember { HazeState() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Box(
            modifier = Modifier.size(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .hazeChild(state = hazeState, style = HazeMaterials.thin())
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                Color.White.copy(alpha = .8f),
                                Color.White.copy(alpha = .2f),
                            )
                        ),
                        shape = CircleShape
                    )
            ) {
                IconButton(
                    onClick = { expanded.value = !expanded.value },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.align(Alignment.TopEnd),
                containerColor = MaterialTheme.colorScheme.background
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.add_new_item)) },
                    onClick = {
                        onItemAddClick()
                        expanded.value = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.create_outfit)) },
                    onClick = {
                        onCreateOutfitClick()
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun ItemCardListCard(
    categoryName: String,
    imageUrls: List<Uri?>,
    hazeState: HazeState = HazeState(),
    navController: NavHostController,
    itemsIds: List<Int>
) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = categoryName,
            style = MaterialTheme.typography.bodySmall
        )
        if(imageUrls.isEmpty()) {
            Box(modifier = Modifier.size(128.dp))
        }
        LazyRow {
            itemsIndexed(imageUrls) { index, imageUrl ->
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .width(128.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent)
                        .hazeChild(state = hazeState, style = HazeMaterials.thin())
                        .border(
                            width = Dp.Hairline,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = .8f),
                                    Color.White.copy(alpha = .2f),
                                )
                            ),
                            shape = RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                        .clickable {
                            navController.navigate(AuthorizedClothitScreens.UpdateItemScreen.name + "/${itemsIds[index]}")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = stringResource(R.string.item_image),
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}