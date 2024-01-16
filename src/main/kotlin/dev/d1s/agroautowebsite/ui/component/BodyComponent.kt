package dev.d1s.agroautowebsite.ui.component

import dev.d1s.agroautowebsite.ui.attrs
import dev.d1s.exkt.kweb.plugins.bootstrap.bsPositionAbsolute
import dev.d1s.exkt.kweb.plugins.bootstrap.bsTop0
import dev.d1s.exkt.kweb.plugins.bootstrap.bsW100
import dev.d1s.exkt.kweb.plugins.bootstrap.bsZN1
import kweb.div
import kweb.html.Document

fun Document.createBody() {
    body {
        navbar()
    }
}