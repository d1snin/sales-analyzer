package dev.d1s.agroautowebsite.ui.component

import dev.d1s.agroautowebsite.ui.Resources
import kweb.*
import kweb.html.Document
import kweb.html.HeadElement
import java.net.URL

private const val TITLE = "ГК АГРОАВТО - Грузоперевозки по РФ и СНГ"
private const val DESCRIPTION =
    "Выгодные грузоперевозки по России и СНГ. Полное сопровождение на всех этапах. Оперативное решение проблем."

// https://colorpicker.me/#b3d4b5
private const val THEME_COLOR = "#b3d4b5"

fun Document.createHead() {
    head {
        createCommonMeta()
        createTitleAndDescription()
        createOpenGraphMeta()
        createTwitterMeta()
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

private fun ElementCreator<HeadElement>.createOpenGraphMeta() {
    meta(name = "og:type", content = "website")
    meta(name = "og:title", content = TITLE)
    meta(name = "og:description", content = DESCRIPTION)
    meta(name = "og:image", content = Resources.BANNER_URL)
}

private fun ElementCreator<HeadElement>.createTwitterMeta() {
    meta(name = "twitter:card", content = "summary_large_image")
    meta(name = "twitter:title", content = TITLE)
    meta(name = "twitter:description", content = DESCRIPTION)
    meta(name = "twitter:image", content = Resources.BANNER_URL)
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