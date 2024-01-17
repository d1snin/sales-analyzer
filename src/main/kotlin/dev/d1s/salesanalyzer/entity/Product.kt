package dev.d1s.salesanalyzer.entity

data class Product(
    val name: String,
    val cost: Double,
    val sell: Double,
    val count: Int,
    val returnRatio: Double,
    val expense: Double
)