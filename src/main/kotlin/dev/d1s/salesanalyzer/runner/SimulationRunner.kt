/*
 * Copyright 2024 Mikhail Titov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.d1s.salesanalyzer.runner

import dev.d1s.salesanalyzer.data.SimulationRepository
import dev.d1s.salesanalyzer.entity.Product
import dev.d1s.salesanalyzer.entity.ProductSale
import dev.d1s.salesanalyzer.entity.ProductSales
import dev.d1s.salesanalyzer.entity.Simulation
import org.lighthousegames.logging.logging
import kotlin.math.roundToInt
import kotlin.random.Random

object SimulationRunner {

    private val log = logging()

    fun runSimulationAndSave(product: Product, session: String) {
        val simulation = runSimulation(product)
        val result = Result.success(simulation)
        SimulationRepository.saveBySession(session, result)
    }

    fun runSimulation(product: Product): Simulation {
        log.i {
            "Running simulation on product: $product"
        }

        val sales = product.doSales()
        val profit = sales.calcProfit(product)
        val totalProfit = sales.calcTotalProfit(product)
        val roi = product.calcRoi(profit)
        val totalExpense = sales.calcTotalExpense(product)
        val balance = sales.last().balance

        val simulation = Simulation(product, sales, profit, totalProfit, roi, totalExpense, balance)

        log.i {
            "Ran simulation: $simulation"
        }

        return simulation
    }

    private fun Product.doSales(): ProductSales =
        buildList {
            var balance = -(expense * count)

            var totalSales = 0
            var successfulSales = 0

            var doSales = true

            while (doSales) {
                val success = Random.nextDouble() > returnRatio
                if (success) {
                    balance += (sell - cost - expense)
                    successfulSales++
                } else {
                    balance -= expense
                }

                val sale = ProductSale(success, balance)
                add(sale)

                totalSales++

                if (successfulSales >= count) {
                    doSales = false
                }
            }
        }

    private fun ProductSales.calcProfit(product: Product) =
        filter { it.success }.size * product.sell

    private fun ProductSales.calcTotalProfit(product: Product) =
        size * product.sell

    private fun Product.calcRoi(profit: Double) =
        (((profit + (sell - cost)) / cost) * 100).roundToInt()

    private fun ProductSales.calcTotalExpense(product: Product) =
        size * product.expense
}