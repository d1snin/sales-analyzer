package dev.d1s.salesanalyzer.entity

@JvmInline
value class ProductSale(
    val success: Boolean
)

data class Simulation(
    val product: Product,
    val sales: ProductSale
)
