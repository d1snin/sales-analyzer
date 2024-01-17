package dev.d1s.salesanalyzer.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.*
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
    div(attrs.bsDFlex) {
        newSimulationButton()
        repeatButton()
    }
}

private fun Component.newSimulationButton() {
    button(attrs.bsBtn.bsBtnOutlineSuccess.bsMe3)
        .configureSimulationModalFormTrigger()
        .text(NEW_SIMULATION_BUTTON_TEXT)
}

private fun Component.repeatButton() {
    button(attrs.bsBtn.bsBtnOutlineSecondary).text(REPEAT_BUTTON_TEXT)
}
