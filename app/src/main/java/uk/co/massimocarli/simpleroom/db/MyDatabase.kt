package uk.co.massimocarli.simpleroom.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Product::class, Price::class),
    views = arrayOf(UkProduct::class),
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDAO
}