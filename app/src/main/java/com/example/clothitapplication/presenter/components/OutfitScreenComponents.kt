package com.example.clothitapplication.presenter.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothitapplication.R
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import com.example.clothitapplication.domain.model.wardrobeModel.Season
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.HazeMaterials


@Composable
fun CreateOutfitScreenHeader(
    title: String = stringResource(R.string.create_outfit),
    onBackArrowCLick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 32.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .clickable { onBackArrowCLick() }
                .size(36.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun AddItemCategoryRow(
    category: String,
    selectedItems: List<WardrobeShortEntity>,
    onNavigateToAddItems: () -> Unit,
    onRemoveItemClicked: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = category,
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AddItemPlaceholder(onImageSelected = onNavigateToAddItems)
            LazyRow(
                modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(selectedItems) { item ->
                    AddItemPlaceholder(
                        itemUri = item.imgUrl,
                        onImageSelected = { onRemoveItemClicked(item.id) })
                }
            }
        }
    }
}

@Composable
fun NextButton(
    onNextButtonClicked: () -> Unit,
    hazeState: HazeState = remember { HazeState() }
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
            modifier = Modifier.align(Alignment.Center),
            onClick = onNextButtonClicked
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = stringResource(R.string.add),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun BackButton(
    onBackButtonClicked: () -> Unit,
    hazeState: HazeState = remember { HazeState() }
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
            modifier = Modifier.align(Alignment.Center),
            onClick = onBackButtonClicked
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.add),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun AddItemPlaceholder(
    itemUri: String = "",
    onImageSelected: () -> Unit,
    hazeState: HazeState = remember { HazeState() }
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    Box(
        modifier = Modifier
            .size(98.dp)
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
            .clickable { onImageSelected() },
        contentAlignment = Alignment.Center
    ) {
        if (itemUri.isNotEmpty()) {
            AsyncImage(
                model = Uri.parse(itemUri),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "Add photo",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
fun OutfitScreenTopBar(
    title: String = stringResource(R.string.create_outfit),
    onBackArrowCLick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 32.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .clickable { onBackArrowCLick() }
                .size(36.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun OutfitCreationForm(
    name: String = "",
    onNameChange: (String) -> Unit = {},
    seasons: List<String> = Season.entries.map { it.name },
    onCategorySelected: (String) -> Unit = {},
    oldCategory: String = "",
    oldDescription: String = "",
    onDescriptionChange: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        NameField(name = name, onNameChange = onNameChange)
        Spacer(modifier = Modifier.height(16.dp))
        CategoryDropDownMenu(
            categories = seasons,
            onCategorySelected = onCategorySelected,
            oldCategory = oldCategory
        )
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionField(
            oldDescription = oldDescription,
            onDescriptionChange = onDescriptionChange
        )
    }
}

@Preview
@Composable
fun NameField(
    name: String = "",
    onNameChange: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Name:",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text("Enter name") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (name.isNotEmpty()) {
                    IconButton(onClick = { onNameChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear name"
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Composable
fun SelectButton(
    onButtonClick: () -> Unit,
    hazeState: HazeState = remember { HazeState() }
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
            modifier = Modifier.align(Alignment.Center),
            onClick = onButtonClick
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.add),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun SummaryForm(
    name: String = "",
    season: String = "",
    description: String = "",
    selectedItems: List<WardrobeShortEntity> = emptyList(),
    hazeState: HazeState = remember { HazeState() }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.summary),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(selectedItems) { item ->
                Box(
                    modifier = Modifier
                        .size(256.dp)
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
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (item.imgUrl.isNotEmpty()) {
                        AsyncImage(
                            model = Uri.parse(item.imgUrl),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add photo",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.name, name),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.season, season),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.description, description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
