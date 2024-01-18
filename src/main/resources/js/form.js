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

function saveInput(elementId) {
    let element = document.getElementById(elementId)
    element.oninput = function (_) {
        localStorage.setItem(elementId, `"${element.value}"`)
    }
}

window.addEventListener("load", function () {
    saveInput("simulation-name-input")
    saveInput("simulation-cost-input")
    saveInput("simulation-sell-input")
    saveInput("simulation-count-input")
    saveInput("simulation-return-ratio-input")
    saveInput("simulation-expense-input")
})