package dev.d1s.sellsanalysiswebsite.ui.component

import dev.d1s.sellsanalysiswebsite.ui.Resources
import kweb.*
import kweb.html.Document
import kweb.html.HeadElement
import java.net.URL

private const val TITLE = "Анализ затрат"
private const val DESCRIPTION =
    "Анализ расходов и прибыли торговой точки."

// https://colorpicker.me/#b3d4b5
private const val THEME_COLOR = "#b3d4b5"

fun Document.createHead() {
    head {
        createCommonMeta()
        createTitleAndDescription()
        createFaviconLinks()
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

private fun ElementCreator<HeadElement>.createFaviconLinks() {
    link(rel = LinkRelationship.icon, href = URL(Resources.APPLE_TOUCH_ICON_URL)).apply {
        this["sizes"] = "180x180"
    }

    link(rel = LinkRelationship.icon, href = URL(Resources.FAVICON_URL))

    link(rel = LinkRelationship.icon, href = URL(Resources.FAVICON_16_URL)).apply {
        this["sizes"] = "16x16"
    }

    link(rel = LinkRelationship.icon, href = URL(Resources.FAVICON_32_URL)).apply {
        this["sizes"] = "32x32"
    }
}