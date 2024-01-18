/*
 * Copyright 2024 Mikhail Titov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.d1s.salesanalyzer.configuration

import dev.d1s.exkt.ktor.server.koin.configuration.ApplicationConfigurer
import dev.d1s.exkt.kweb.plugins.bootstrap.bootstrapIconsPlugin
import dev.d1s.exkt.kweb.plugins.bootstrap.bootstrapPlugin
import dev.d1s.salesanalyzer.ui.component.createBody
import dev.d1s.salesanalyzer.ui.component.createHead
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.websocket.*
import kweb.Kweb
import kweb.installKwebOnRemainingRoutes
import kweb.plugins.css.CSSPlugin
import kweb.plugins.javascript.JavascriptPlugin
import org.koin.core.module.Module

object Website : ApplicationConfigurer {

    override fun Application.configure(module: Module, config: ApplicationConfig) {
        install(DefaultHeaders)
        install(Compression)

        install(WebSockets)

        install(Kweb) {
            val cssPlugin = CSSPlugin("css", "main.css")
            val jsPlugin = JavascriptPlugin("js", "form.js")

            plugins = listOf(bootstrapPlugin, bootstrapIconsPlugin, cssPlugin, jsPlugin)
        }

        installKwebOnRemainingRoutes {
            doc.apply {
                createHead()
                createBody()
            }
        }
    }
}