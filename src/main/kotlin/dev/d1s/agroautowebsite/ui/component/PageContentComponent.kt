package dev.d1s.agroautowebsite.ui.component

import dev.d1s.agroautowebsite.ui.attrs
import dev.d1s.agroautowebsite.ui.uiFancyBg
import dev.d1s.exkt.kweb.plugins.bootstrap.bsContainer
import dev.d1s.exkt.kweb.plugins.bootstrap.bsContainerFluid
import dev.d1s.exkt.kweb.plugins.bootstrap.bsMt4
import kweb.components.Component
import kweb.div

fun Component.pageContent() {
    div(attrs.bsContainerFluid.bsMt4.uiFancyBg) {
        div(attrs.bsContainer) {
        }

        footer()
    }
}
