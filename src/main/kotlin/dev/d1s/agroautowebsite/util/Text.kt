package dev.d1s.agroautowebsite.util

import dev.d1s.agroautowebsite.ui.attrs
import dev.d1s.agroautowebsite.ui.uiTextGreen
import kweb.AttributeBuilder
import kweb.components.Component
import kweb.span

class Text {

    val parts = mutableListOf<TextPart>()

    fun common(text: String): Text {
        parts += TextPart(attrs, text)
        return this
    }
    data class TextPart(val attrs: AttributeBuilder, val text: String)
}

fun Component.text(text: Text) {
    text.parts.forEach {
        span(it.attrs).text(it.text)
    }
}