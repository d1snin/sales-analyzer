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
import dev.d1s.salesanalyzer.data.SimulationRepository
import dev.d1s.salesanalyzer.entity.Product
import dev.d1s.salesanalyzer.entity.Simulation
import dev.d1s.salesanalyzer.runner.SimulationRunner
import dev.d1s.salesanalyzer.ui.attrs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kweb.*
import kweb.components.Component
import org.lighthousegames.logging.logging

private const val MODAL_ID = "simulation-form-modal"

private const val FORM_TITLE = "Описание товара"

private const val FORM_NAME_ID = "simulation-name-input"
private const val FORM_NAME_LABEL = "Наименование"

private const val FORM_COST_ID = "simulation-cost-input"
private const val FORM_COST_LABEL = "Себестоимость"

private const val FORM_SELL_ID = "simulation-sell-input"
private const val FORM_SELL_LABEL = "Стоимость продажи"

private const val FORM_COUNT_ID = "simulation-count-input"
private const val FORM_COUNT_LABEL = "Кол-во штук в одной партии"

private const val FORM_RETURN_RATIO_ID = "simulation-return-ratio-input"
private const val FORM_RETURN_RATIO_VALUE_ID = "simulation-return-ration-input-value"
private const val FORM_RETURN_RATIO_LABEL = "% возвратов"

private const val FORM_EXPENSE_ID = "simulation-expense-input"
private const val FORM_EXPENSE_LABEL = "Издержки"

private const val START_BUTTON_TEXT = "Запустить симуляцию"

private const val INCORRECT_DATA_MESSAGE = "Введены неверные данные."

private val formHandlerScope = CoroutineScope(Dispatchers.IO)

private val log = logging()

fun Component.simulationModalForm() {
    div(attrs.bsModal.bsFade.id(MODAL_ID))
        .set("tabindex", "-1")
        .new {
            div(attrs.bsModalDialog) {
                div(attrs.bsModalContent.bsBgDark) {
                    div(attrs.bsModalHeader.bsBorderSuccess) {
                        h1(attrs.bsModalTitle.bsFs5).text(FORM_TITLE)

                        @Suppress("ReplaceGetOrSet")
                        button(attrs.bsBtnClose.bsTextBgDanger)
                            .set("type", "button")
                            .set("data-bs-dismiss", "modal")
                    }

                    div(attrs.bsModalBody) {
                        modalForm()
                    }
                }
            }
        }
}

fun <E : Element> E.configureSimulationModalFormTrigger(): E {
    set("data-bs-toggle", "modal")
    set("data-bs-target", "#$MODAL_ID")

    return this
}

private fun Component.modalForm() {
    form {
        textInput(FORM_NAME_ID, FORM_NAME_LABEL)
        textInput(FORM_COST_ID, FORM_COST_LABEL, type = InputType.number)
        textInput(FORM_SELL_ID, FORM_SELL_LABEL, type = InputType.number)
        textInput(FORM_COUNT_ID, FORM_COUNT_LABEL, type = InputType.number)
        rangeInput(FORM_RETURN_RATIO_ID, FORM_RETURN_RATIO_LABEL)
        textInput(FORM_EXPENSE_ID, FORM_EXPENSE_LABEL, type = InputType.number)

        button(attrs.bsBtn.bsBtnOutlineSuccess, type = ButtonType.submit)
            .set("data-bs-dismiss", "modal")
            .text(START_BUTTON_TEXT)
            .on(preventDefault = true).click {
                handleStart()
            }
    }
}

private fun Component.textInput(id: String, label: String, type: InputType = InputType.text) {
    div(attrs.bsMb3) {
        label(attrs.bsFormLabel).setFor(id).text(label)
        input(attrs.bsFormControl.bsBgDark.bsTextLight.id(id), type = type)
    }
}

private fun Component.rangeInput(id: String, label: String) {
    fun syncInputValue() {
        browser.callJsFunction(
            "document.getElementById('$FORM_RETURN_RATIO_VALUE_ID').innerText = (document.getElementById('$FORM_RETURN_RATIO_ID').value) + \"%\""
        )
    }

    div(attrs.bsMb3) {
        label(attrs.bsFormLabel).setFor(id).text(label)
        input(attrs.bsFormRange.id(id), type = InputType.range)
            .on.input {
                syncInputValue()
            }
        span(attrs.id(FORM_RETURN_RATIO_VALUE_ID))
    }

    syncInputValue()
}

private fun Component.handleStart() {
    suspend fun localStorageValue(key: String): String =
        requireNotNull(browser.doc.localStorage.get(key))

    formHandlerScope.launch {
        val rawName = localStorageValue(FORM_NAME_ID)
        val rawCost = localStorageValue(FORM_COST_ID)
        val rawSell = localStorageValue(FORM_SELL_ID)
        val rawCount = localStorageValue(FORM_COUNT_ID)
        val rawReturnRatio = localStorageValue(FORM_RETURN_RATIO_ID)
        val rawExpense = localStorageValue(FORM_EXPENSE_ID)

        val session = browser.sessionId

        val product = try {
            Product(
                name = rawName,
                cost = rawCost.toDouble(),
                sell = rawSell.toDouble(),
                count = rawCount.toInt(),
                returnRatio = rawReturnRatio.toInt() / 100.0,
                expense = rawExpense.toDouble()
            )
        } catch (e: Exception) {
            log.e(e) {
                "Failure when parsing form"
            }

            val result = Result.failure<Simulation>(IllegalArgumentException(INCORRECT_DATA_MESSAGE))
            SimulationRepository.saveBySession(session, result)

            return@launch
        }

        val validation = product.validate()

        validation.onSuccess {
            log.i {
                "New product: $product"
            }

            SimulationRunner.runSimulationAndSave(product, session)
        }

        validation.onFailure {
            val result = Result.failure<Simulation>(it)
            SimulationRepository.saveBySession(session, result)
        }
    }
}

private fun Product.validate() =
    runCatching {
        if (name.isBlank()) {
            error("Имя товара не может быть пустым.")
        }

        if (cost < 1) {
            error("Себестоимость не может быть меньше единицы.")
        }

        if (sell < cost) {
            error("Цена продажи не может быть меньше себестоимости.")
        }

        if (count < 1) {
            error("Кол-во не может быть меньше единицы.")
        }

        if (count > 1000) {
            error("Кол-во не может быть больше тысячи.")
        }

        if (returnRatio > 90.0) {
            error("Процент возвратов не должен превышать 90%.")
        }

        if (expense < 1) {
            error("Издержки не могут быть меньше единицы.")
        }
    }