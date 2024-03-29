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

package dev.d1s.salesanalyzer

import dev.d1s.exkt.ktor.server.koin.configuration.Configurers
import dev.d1s.exkt.ktor.server.koin.configuration.ServerApplication
import dev.d1s.exkt.ktor.server.koin.configuration.builtin.Connector
import dev.d1s.exkt.ktor.server.koin.configuration.builtin.Di
import dev.d1s.salesanalyzer.configuration.ApplicationConfigBean
import dev.d1s.salesanalyzer.configuration.Website
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.component.KoinComponent
import org.lighthousegames.logging.logging

class SalesAnalyzerApplication : ServerApplication(), KoinComponent {

    override val configurers: Configurers = listOf(
        Connector,
        ApplicationConfigBean,
        Website,
        Di
    )

    private val logger = logging()

    override fun launch() {
        logger.i {
            "Starting website..."
        }

        val applicationEngineEnvironment = createApplicationEngineEnvironment()

        embeddedServer(Netty, applicationEngineEnvironment).start(wait = true)
    }
}