package com.example.clothitapplication.presenter.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.clothitapplication.R
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory
import coil3.compose.AsyncImage
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.HazeMaterials


@Composable
fun ItemScreenTopBar(
    title: String = stringResource(R.string.add_new_item),
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
fun CategoryDropDownMenu(
    categories: List<String> = WardrobeCategory.entries.map { it.name },
    onCategorySelected: (String) -> Unit = {},
    oldCategory: String = ""
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(oldCategory) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.choose_category),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Box(modifier = Modifier.fillMaxWidth().
        clickable(onClick = { expanded = true})) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text(text = "Category") },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            onCategorySelected(category)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DescriptionField(
    oldDescription: String = "",
    onDescriptionChange: (String) -> Unit = {}
) {
    var description by remember { mutableStateOf(oldDescription) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Add description",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
                onDescriptionChange(it)
            }, label = { Text("Add description") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (description.isNotEmpty()) {
                    IconButton(onClick = {
                        description = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear description"
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
fun ImagePlaceholder(
    onImageSelected: (Uri) -> Unit,
    hazeState: HazeState = remember { HazeState() }) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        selectedImageUri = uri
    }

    Box(
        modifier = Modifier
            .height(Size.width().dp)
            .width(Size.width().dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(32.dp))
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
            .clickable { launcher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if (selectedImageUri != null) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            onImageSelected(selectedImageUri!!)
        } else {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Add photo",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun AddButton(
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
fun FilledImagePlaceholder(
    imageUri: Uri,
    onImageSelected: (Uri) -> Unit,
    hazeState: HazeState = remember { HazeState() }
){
    var selectedImageUri by remember { mutableStateOf<Uri?>(imageUri) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        selectedImageUri = uri
    }

    Box(
        modifier = Modifier
            .height(Size.width().dp)
            .width(Size.width().dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(32.dp))
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
            .clickable { launcher.launch("image/*") },
        contentAlignment = Alignment.Center
    ) {
        if (selectedImageUri != null) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            onImageSelected(selectedImageUri!!)
        } else {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Add photo",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SelectImagePlaceholder(
    imageUri: Uri,
    itemId: Int,
    hazeState: HazeState = remember { HazeState() },
    itemIds: List<Int>,
    onItemSelected: (List<Int>) -> Unit
) {
    var isSelected by remember { mutableStateOf(itemIds.contains(itemId)) }

    Box(
        modifier = Modifier
            .size(128.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(if (isSelected) Color.Gray.copy(alpha = 0.2f) else Color.Transparent)
            .hazeChild(state = hazeState, style = HazeMaterials.thin())
            .border(
                width = Dp.Hairline,
                brush = Brush.verticalGradient(
                    colors = if (isSelected) {
                        listOf(
                            Color.Gray.copy(alpha = 0.8f),
                            Color.Gray.copy(alpha = 0.4f)
                        )
                    } else {
                        listOf(
                            Color.White.copy(alpha = 0.8f),
                            Color.White.copy(alpha = 0.2f)
                        )
                    }
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                isSelected = !isSelected
                val newItemIds = if (isSelected) {
                    itemIds + itemId
                } else {
                    itemIds - itemId
                }
                onItemSelected(newItemIds)
            },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != Uri.EMPTY) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Add photo",
                tint = if (isSelected) Color.Gray else MaterialTheme.colorScheme.onSurface
            )
        }

        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.8f))
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

