package dev.d1s.agroautowebsite.ui.component

import dev.d1s.agroautowebsite.ui.*
import dev.d1s.agroautowebsite.util.Text
import dev.d1s.agroautowebsite.util.text
import dev.d1s.exkt.kweb.plugins.bootstrap.*
import kweb.a
import kweb.components.Component
import kweb.div
import kweb.img
import kweb.p

private const val FOOTER_MAIN_LINK = "АГРО АВТО"

private const val FOOTER_SERVICE_LINK = "Услуги"
private const val FOOTER_REGIONS_LINK = "Регионы"
private const val FOOTER_ABOUT_LINK = "О нас"
private const val FOOTER_CONTACTS_LINK = "Контакты"

private const val LEGAL_MAIL = "legal@gk-agroauto.ru"
private const val LEGAL_MAIL_URL = "mailto:legal@gk-agroauto.ru"

private val legalText = Text()
    .common(
        "ИП Беляева Ю. А., "
    )

fun Component.footer() {
    div(attrs.bsContainerFluid.bsDFlex.bsJustifyContentCenter.bsP5.uiBlurredBg) {
        div(attrs.bsContainer) {
            div(attrs.bsRow.bsRowCols1.bsRowColsMd2.bsW100.bsJustifyContentStart.bsJustifyContentMdCenter.bsAlignItemsCenter) {
                div(attrs.bsCol.bsDFlex.bsFlexColumn.bsJustifyContentCenter) {
                    logoAndName()
                    legal()
                }

                div(attrs.bsCol.bsRow.bsRowCols1.bsRowColsMd2.bsG3) {
                    link(href = Paths.SERVICE, text = FOOTER_SERVICE_LINK)
                    link(href = Paths.REGIONS, text = FOOTER_REGIONS_LINK)
                    link(href = Paths.ABOUT, text = FOOTER_ABOUT_LINK)
                    link(href = Paths.CONTACTS, text = FOOTER_CONTACTS_LINK)
                }
            }
        }
    }
}

private fun Component.logoAndName() {
    div(attrs.bsDFlex.bsW100.bsAlignItemsCenter.bsMeMd5.bsMb2) {
        a(attrs.bsMe3, href = Paths.MAIN, preventDefault = false) {
            img(attrs.uiFooterLogo)["src"] = Resources.LOGO_URL
        }

        div(attrs.bsDFlex.bsFlexColumn.bsNavbarBrand.bsMe0.bsMeMd5) {
            a(attrs.bsFs2.bsTextDecorationNone.uiHeading, href = Paths.MAIN, preventDefault = false)
                .text(FOOTER_MAIN_LINK)
        }
    }
}

private fun Component.legal() {
    p(attrs.bsMb0.bsFs6.uiTextGray) {
        text(legalText)
        a(attrs.bsTextDecorationNone.bsTextReset, href = LEGAL_MAIL_URL).text(LEGAL_MAIL)
    }
}

private fun Component.link(href: String, text: String) {
    a(attrs.bsColAuto.uiTextGray.bsFs5.bsFwBold.bsTextDecorationNone, href = href, preventDefault = false).text(text)
}