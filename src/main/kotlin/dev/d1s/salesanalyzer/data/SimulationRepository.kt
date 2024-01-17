package dev.d1s.salesanalyzer.data

import dev.d1s.salesanalyzer.entity.Simulation
import java.util.concurrent.ConcurrentHashMap

object SimulationRepository {

    private val simulations = ConcurrentHashMap<String, Simulation>()

    fun findBySession(sessionId: String): Simulation? =
        simulations[sessionId]

    fun saveBySession(sessionId: String, simulation: Simulation) {
        simulations[sessionId] = simulation
    }
}