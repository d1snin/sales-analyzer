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

package dev.d1s.salesanalyzer.ui.component

import kweb.ElementCreator
import kweb.html.Document
import kweb.html.HeadElement
import kweb.meta
import kweb.title
import kweb.viewport

private const val TITLE = "Анализ продаж"
private const val DESCRIPTION =
    "Анализ расходов и прибыли торговой точки."

// https://colorpicker.me/#b3d4b5
private const val THEME_COLOR = "#b3d4b5"

fun Document.createHead() {
    head {
        createCommonMeta()
        createTitleAndDescription()
    }
}

private fun ElementCreator<HeadElement>.createCommonMeta() {
    meta(charset = "utf-8")
    viewport()
    meta(name = "theme-color", content = THEME_COLOR)
}

private fun ElementCreator<HeadElement>.createTitleAndDescription() {
    title().text(TITLE)
    meta(name = "title", content = TITLE)
    meta(name = "description", content = DESCRIPTION)
}