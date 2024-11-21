package com.example.clothitapplication.data.repository.local
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.clothitapplication.data.dto.ItemDto
import com.example.clothitapplication.data.dto.OutfitDto

@Database(entities = [ItemDto::class, OutfitDto::class], version = 2)
@TypeConverters(Converters::class)
abstract class ClothitDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun outfitDao(): OutfitDao
}