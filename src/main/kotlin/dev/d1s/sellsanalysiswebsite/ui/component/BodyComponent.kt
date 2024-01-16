package dev.d1s.sellsanalysiswebsite.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.bsContainer
import dev.d1s.exkt.kweb.plugins.bootstrap.bsDFlex
import dev.d1s.exkt.kweb.plugins.bootstrap.bsJustifyContentStart
import dev.d1s.exkt.kweb.plugins.bootstrap.bsPt5
import dev.d1s.sellsanalysiswebsite.ui.attrs
import kweb.div
import kweb.html.Document

fun Document.createBody() {
    body {
        div(attrs.bsContainer.bsDFlex.bsJustifyContentStart.bsPt5) {
            heading()
        }
    }
}