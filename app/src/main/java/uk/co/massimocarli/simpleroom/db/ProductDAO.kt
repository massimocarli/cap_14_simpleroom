package uk.co.massimocarli.simpleroom.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ProductDAO {

    @Query("SELECT * FROM product WHERE id = :pid")
    fun findById(pid: Int): Product?

    @Query("SELECT * FROM product")
    @Transaction
    fun findAll(): List<ProductResult>

    @Query("SELECT * FROM UkProduct")
    fun findAllWithView(): List<UkProduct>
}

