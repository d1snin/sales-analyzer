package dev.d1s.sellsanalysiswebsite.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.bsBtn
import dev.d1s.exkt.kweb.plugins.bootstrap.bsBtnOutlineSecondary
import dev.d1s.exkt.kweb.plugins.bootstrap.bsBtnOutlineSuccess
import dev.d1s.exkt.kweb.plugins.bootstrap.bsDisplay3
import dev.d1s.sellsanalysiswebsite.ui.attrs
import kweb.button
import kweb.components.Component
import kweb.h1

private const val HEADING = "Анализ продаж торговой точки"

private const val NEW_SIMULATION_BUTTON_TEXT = "Новая симуляция"
private const val REPEAT_BUTTON_TEXT = "Повторить симуляцию"

fun Component.heading() {
    h1(attrs.bsDisplay3).text(HEADING)
    buttons()
}

private fun Component.buttons() {
    newSimulationButton()
    repeatButton()
}

private fun Component.newSimulationButton() {
    button(attrs.bsBtn.bsBtnOutlineSuccess).text(NEW_SIMULATION_BUTTON_TEXT)
}

private fun Component.repeatButton() {
    button(attrs.bsBtn.bsBtnOutlineSecondary).text(REPEAT_BUTTON_TEXT)
}
