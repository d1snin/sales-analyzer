package dev.d1s.salesanalyzer.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.*
import dev.d1s.salesanalyzer.data.SimulationRepository
import dev.d1s.salesanalyzer.ui.attrs
import kweb.components.Component
import kweb.div
import kweb.i
import kweb.new
import kweb.span

private const val NO_SIMULATION_MESSAGE = "Вы пока не создали ни одной симуляции."

fun Component.simulationContent() {
    div(attrs.bsMt3) {
        val simulation = SimulationRepository.findBySession(browser.sessionId)

        if (simulation != null) {

        } else {
            noSimulationAlert()
        }
    }
}

private fun Component.noSimulationAlert() {
    div(attrs.bsAlert.bsFs4)
        .set("role", "alert")
        .new {
            i(attrs.bi.biInfoCircle.bsMe2.bsTextWarning)
            span().text(NO_SIMULATION_MESSAGE)
        }
}