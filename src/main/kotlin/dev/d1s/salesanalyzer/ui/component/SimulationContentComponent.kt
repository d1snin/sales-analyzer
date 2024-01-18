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

package dev.d1s.salesanalyzer.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.*
import dev.d1s.salesanalyzer.data.renderWithCurrentSimulation
import dev.d1s.salesanalyzer.entity.Simulation
import dev.d1s.salesanalyzer.ui.attrs
import kweb.*
import kweb.components.Component

fun Component.simulationContent() {
    div(attrs.bsDFlex.bsW100.bsMt4) {
        renderWithCurrentSimulation { simulation ->
            simulation.onSuccess {
                resultTable(simulation = it)
            }

            simulation.onFailure {
                noSimulationAlert(it.message ?: "Что-то пошло не так.")
            }
        }
    }
}

private fun Component.resultTable(simulation: Simulation) {
    fun Component.row(name: String, value: String) {
        tr {
            th().set("scope", "row").text(name)
            td().text(value)
        }
    }

    table(attrs.bsW100.bsTable.bsTableDark.bsTableStripedColumns) {
        tbody {
            row(name = "Наименование товара", value = simulation.product.name)
            row(name = "Количество реализаций", value = simulation.sales.size.toString())
            row(name = "Итоговая прибыль", value = simulation.profit.toString())
            row(name = "Валовая прибыль", value = simulation.totalProfit.toString())
            row(name = "Коэффициент ROI", value = "${simulation.roi}%")
            row(name = "Итоговые издержки", value = simulation.totalExpense.toString())
            row(name = "Остаток счета", value = simulation.balance.toString())
        }
    }
}

private fun Component.noSimulationAlert(message: String) {
    div(attrs.bsAlert.bsFs4)
        .set("role", "alert")
        .new {
            i(attrs.bi.biInfoCircle.bsMe2.bsTextDanger)
            span().text(message)
        }
}