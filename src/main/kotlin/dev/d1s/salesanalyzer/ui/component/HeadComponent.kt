package dev.d1s.salesanalyzer.ui.component

import kweb.ElementCreator
import kweb.html.Document
import kweb.html.HeadElement
import kweb.meta
import kweb.title
import kweb.viewport

private const val TITLE = "Анализ продаж"
private const val DESCRIPTION =
    "Анализ расходов и прибыли торговой точки."

// https://colorpicker.me/#b3d4b5
private const val THEME_COLOR = "#b3d4b5"

fun Document.createHead() {
    head {
        createCommonMeta()
        createTitleAndDescription()
    }
}

private fun ElementCreator<HeadElement>.createCommonMeta() {
    meta(charset = "utf-8")
    viewport()
    meta(name = "theme-color", content = THEME_COLOR)
}

private fun ElementCreator<HeadElement>.createTitleAndDescription() {
    title().text(TITLE)
    meta(name = "title", content = TITLE)
    meta(name = "description", content = DESCRIPTION)
}