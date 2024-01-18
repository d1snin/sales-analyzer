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
import dev.d1s.salesanalyzer.runner.SimulationRunner
import dev.d1s.salesanalyzer.ui.attrs
import kweb.button
import kweb.components.Component
import kweb.div
import kweb.h1

private const val HEADING = "Анализ продаж торговой точки"

private const val NEW_SIMULATION_BUTTON_TEXT = "Новая симуляция"
private const val REPEAT_BUTTON_TEXT = "Повторить симуляцию"

fun Component.heading() {
    div(attrs.bsDFlex.bsFlexColumn) {
        h1(attrs.bsDisplay3.bsMb3).text(HEADING)
        buttons()
    }
}

private fun Component.buttons() {
    renderWithCurrentSimulation { simulation ->
        div(attrs.bsDFlex) {
            newSimulationButton()

            simulation.onSuccess {
                repeatButton(simulation = it)
            }
        }
    }
}

private fun Component.newSimulationButton() {
    button(attrs.bsBtn.bsBtnOutlineSuccess.bsMe3)
        .configureSimulationModalFormTrigger()
        .text(NEW_SIMULATION_BUTTON_TEXT)
}

private fun Component.repeatButton(simulation: Simulation) {
    button(attrs.bsBtn.bsBtnOutlineSecondary)
        .text(REPEAT_BUTTON_TEXT)
        .on.click {
            SimulationRunner.runSimulationAndSave(simulation.product, browser.sessionId)
        }
}
