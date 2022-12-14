package com.example.ts_state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ts_state.statemachine.Turnstile
import com.example.ts_state.statemachine.TurnstileEvent
import com.example.ts_state.statemachine.TurnstileStateMachineFactory

class TurnstileViewModel(
    turnstileStateMachineFactory: TurnstileStateMachineFactory,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), Turnstile {

    enum class UiState {
        UNLOCKED,
        LOCKED,
        THANKING,
        ALARMING,
    }

    val uiState = MutableLiveData(UiState.LOCKED)

    private val turnstileStateMachine by lazy {
        fun getStrategy() = savedStateHandle.get<String>(
            TurnstileFragment.STRATEGY_NAME_KEY
        ).orEmpty()

        turnstileStateMachineFactory.create(
            strategy = getStrategy(),
            turnstile = this
        )
    }

    fun insertCoin() {
        turnstileStateMachine.handleEvent(TurnstileEvent.COIN)
    }

    fun pass() {
        turnstileStateMachine.handleEvent(TurnstileEvent.PASS)
    }

    override fun lock() {
        updateAction(UiState.LOCKED)
    }

    override fun unlock() {
        updateAction(UiState.UNLOCKED)
    }

    override fun thanks() {
        updateAction(UiState.THANKING)
    }

    override fun alarm() {
        updateAction(UiState.ALARMING)
    }

    private fun updateAction(action: UiState) {
        uiState.value = action
    }

    @Suppress("UNCHECKED_CAST")
    object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return TurnstileViewModel(
                TurnstileStateMachineFactory(),
                extras.createSavedStateHandle(),
            ) as T
        }
    }
}