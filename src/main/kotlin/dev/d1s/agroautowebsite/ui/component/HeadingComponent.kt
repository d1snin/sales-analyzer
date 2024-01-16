package dev.d1s.agroautowebsite.ui.component

import dev.d1s.agroautowebsite.ui.attrs
import dev.d1s.agroautowebsite.ui.uiHeading
import dev.d1s.exkt.kweb.plugins.bootstrap.bsBorderTop
import dev.d1s.exkt.kweb.plugins.bootstrap.bsMt4
import dev.d1s.exkt.kweb.plugins.bootstrap.bsPt4
import kweb.components.Component
import kweb.h1
import kweb.id

fun Component.heading(text: String, id: String? = null, includeBorder: Boolean = true) {
    h1(attrs.uiHeading.apply {
        id?.let {
            id(id)
        }

        if (includeBorder) {
            bsPt4.bsMt4.bsBorderTop
        }
    }).text(text)
}