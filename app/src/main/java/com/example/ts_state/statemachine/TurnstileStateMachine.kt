package com.example.ts_state.statemachine

interface TurnstileStateMachine {
    fun handleEvent(event: TurnstileEvent)
}

enum class TurnstileEvent {
    COIN,
    PASS,
}
