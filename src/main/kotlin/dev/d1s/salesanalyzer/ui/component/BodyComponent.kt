package dev.d1s.salesanalyzer.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.*
import dev.d1s.salesanalyzer.ui.attrs
import kweb.div
import kweb.html.Document

fun Document.createBody() {
    body {
        div(attrs.bsContainer.bsDFlex.bsFlexColumn.bsAlignItemsStart.bsPt5) {
            heading()
            simulationContent()
        }

        simulationModalForm()
    }
}