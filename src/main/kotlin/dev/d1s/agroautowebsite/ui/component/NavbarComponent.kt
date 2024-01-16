package dev.d1s.agroautowebsite.ui.component

import dev.d1s.agroautowebsite.ui.*
import dev.d1s.exkt.kweb.plugins.bootstrap.*
import kweb.*
import kweb.components.Component

private const val NAVBAR_MAIN_LINK = "АГРО АВТО"

private const val NAVBAR_ID = "ui-main-navbar"

private const val NAVBAR_SERVICE_LINK = "Услуги"
private const val NAVBAR_REGIONS_LINK = "Регионы"
private const val NAVBAR_ABOUT_LINK = "О нас"
private const val NAVBAR_CONTACTS_LINK = "Контакты"

fun Component.navbar() {
    nav(attrs.bsNavbar.bsNavbarExpandXl.bsStickyTop.bsBgTransparent.bsMt0.bsPt3.bsFs4.uiBlurredBg) {
        div(attrs.bsContainer) {
            logoAndName()
            toggler()

            div(attrs.bsCollapse.bsNavbarCollapse.bsJustifyContentEnd.id(NAVBAR_ID)) {
                ul(attrs.bsNavbarNav) {
                    navItem(href = Paths.SERVICE, icon = attrs.bi.biSearch, text = NAVBAR_SERVICE_LINK)
                    navItem(href = Paths.REGIONS, icon = attrs.bi.biCompass, text = NAVBAR_REGIONS_LINK)
                    navItem(href = Paths.ABOUT, icon = attrs.bi.biQuestionCircle, text = NAVBAR_ABOUT_LINK)
                    navItem(href = Paths.CONTACTS, icon = attrs.bi.biTelephone, text = NAVBAR_CONTACTS_LINK)
                }
            }
        }
    }
}

private fun Component.logoAndName() {
    a(attrs.bsNavbarBrand.bsMe3, href = Paths.MAIN, preventDefault = false) {
        img(attrs.uiNavbarLogo)["src"] = Resources.LOGO_URL
    }

    div(attrs.bsDFlex.bsFlexColumn.bsNavbarBrand.bsMe4) {
        a(attrs.bsFs2.bsTextDecorationNone.uiHeading, href = Paths.MAIN, preventDefault = false)
            .text(NAVBAR_MAIN_LINK)
    }
}

private fun Component.toggler() {
    button(attrs.bsNavbarToggler.bsMsAuto.uiNavbarTogglerButton)
        .set("data-bs-toggle", "collapse")
        .set("data-bs-target", "#$NAVBAR_ID")
        .set("aria-controls", NAVBAR_ID)
        .set("aria-expanded", "false")
        .set("aria-label", "Toggle navigation")
        .new {
            span(attrs.bsNavbarTogglerIcon)
        }
}

private fun Component.navItem(href: String, icon: AttributeBuilder, text: String) {
    li(attrs.bsNavItem.bsTextDark.bsMe3) {
        a(attrs.bsNavLink, href = href, preventDefault = false) {
            i(icon.bsMe2)
            span().text(text)
        }
    }
}