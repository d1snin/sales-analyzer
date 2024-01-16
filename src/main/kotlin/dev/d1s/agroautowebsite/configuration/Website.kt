package dev.d1s.agroautowebsite.configuration

import dev.d1s.agroautowebsite.ui.component.createBody
import dev.d1s.agroautowebsite.ui.component.createHead
import dev.d1s.exkt.ktor.server.koin.configuration.ApplicationConfigurer
import dev.d1s.exkt.kweb.plugins.bootstrap.bootstrapIconsPlugin
import dev.d1s.exkt.kweb.plugins.bootstrap.bootstrapPlugin
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.websocket.*
import kweb.Kweb
import kweb.installKwebOnRemainingRoutes
import kweb.plugins.css.CSSPlugin
import org.koin.core.module.Module

object Website : ApplicationConfigurer {

    override fun Application.configure(module: Module, config: ApplicationConfig) {
        install(DefaultHeaders)
        install(Compression)

        install(WebSockets)

        install(Kweb) {
            val cssPlugin = CSSPlugin("css", "main.css")
            plugins = listOf(bootstrapPlugin, bootstrapIconsPlugin, cssPlugin)
        }

        installKwebOnRemainingRoutes {
            doc.apply {
                createHead()
                createBody()
            }
        }
    }
}