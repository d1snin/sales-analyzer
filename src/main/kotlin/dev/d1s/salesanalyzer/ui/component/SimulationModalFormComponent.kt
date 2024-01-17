package dev.d1s.salesanalyzer.ui.component

import dev.d1s.exkt.kweb.plugins.bootstrap.*
import dev.d1s.salesanalyzer.ui.attrs
import kweb.*
import kweb.components.Component

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

        button(attrs.bsBtn.bsBtnOutlineSuccess)
            .set("data-bs-dismiss", "modal")
            .text(START_BUTTON_TEXT)
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