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

import dev.d1s.exkt.kweb.plugins.bootstrap.*
import dev.d1s.salesanalyzer.ui.attrs
import kweb.a
import kweb.components.Component
import kweb.div
import kweb.i

private const val PROJECT_URL = "https://github.com/d1snin/sales-analyzer"
private const val LINK_TEXT = "Код на GitHub"

fun Component.footer() {
    div(attrs.bsW100.bsMt4.bsDFlex.bsJustifyContentCenter.bsTextSecondary) {
        i(attrs.bi.biGithub.bsTextSecondary.bsMe2)
        a(attrs.bsTextReset, href = PROJECT_URL).text(LINK_TEXT)
    }
}