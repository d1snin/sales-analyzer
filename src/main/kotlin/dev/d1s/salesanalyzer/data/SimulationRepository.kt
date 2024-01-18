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

package dev.d1s.salesanalyzer.data

import dev.d1s.salesanalyzer.entity.Simulation
import dev.d1s.salesanalyzer.entity.SimulationState
import java.util.concurrent.ConcurrentHashMap

object SimulationRepository {

    private const val NO_SIMULATION_MESSAGE = "Вы еще не создали ни одной симуляции."

    private val simulations = ConcurrentHashMap<String, SimulationState>()

    fun getBySession(sessionId: String): SimulationState =
        simulations.getOrPut(sessionId) {
            val result = Result.failure<Simulation>(IllegalStateException(NO_SIMULATION_MESSAGE))
            SimulationState(result)
        }

    fun saveBySession(sessionId: String, simulation: Result<Simulation>): SimulationState =
        simulations[sessionId]?.let {
            it.value = simulation
            it
        } ?: run {
            val state = SimulationState(simulation)
            simulations[sessionId] = state
            state
        }
}