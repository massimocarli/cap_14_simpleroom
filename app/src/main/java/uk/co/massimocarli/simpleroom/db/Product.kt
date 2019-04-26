package uk.co.massimocarli.simpleroom.db

import androidx.room.*

@Entity
data class Product(
    @PrimaryKey
    var id: Int,
    var name: String,
    var description: String?,
    @Embedded(prefix = "media_")
    var media: Media
)

@Entity
data class Media(
    val file: String,
    val description: String?
)

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("product_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.SET_NULL,
            deferred = true
        )
    ),
    indices = arrayOf(Index(name = "product_id_index", value = ["product_id"]))
)
data class Price(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "price_id") var id: Int?,
    var currency: String,
    var value: Float?,
    @ColumnInfo(name = "product_id")
    var productId: Int?
)


data class ProductResult(
    @Embedded
    var product: Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "product_id",
        entity = Price::class,
        projection = ["currency"]
    )
    var prices: List<Price>
)