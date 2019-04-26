package uk.co.massimocarli.simpleroom.db

import androidx.room.DatabaseView

@DatabaseView(
    viewName = "UkProduct",
    value = "SELECT product.id, product.name, product.description, " +
            "price.currency AS currency, price.value as value, " +
            "product.media_file as media FROM product " +
            "INNER JOIN price ON product.id = price.product_id " +
            "WHERE currency='GBP'"
)
data class UkProduct(
    var id: Long,
    var name: String?,
    var description: String?,
    var currency: String?,
    var value: Float?
)