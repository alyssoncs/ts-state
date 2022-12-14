package com.example.ts_state.statemachine.nestedswitchcase

import com.example.ts_state.statemachine.Turnstile
import com.example.ts_state.statemachine.TurnstileEvent
import com.example.ts_state.statemachine.TurnstileStateMachine

class NestedSwitchCaseTurnstileStateMachine(
    private val turnstile: Turnstile,
) : TurnstileStateMachine {

    private var currentState = State.LOCKED

    override fun handleEvent(event: TurnstileEvent) {
        when (currentState) {
            State.LOCKED -> when (event) {
                TurnstileEvent.COIN -> {
                    turnstile.unlock()
                    currentState = State.UNLOCKED
                }
                TurnstileEvent.PASS -> {
                    turnstile.alarm()
                }
            }
            State.UNLOCKED -> when (event) {
                TurnstileEvent.COIN -> {
                    turnstile.thanks()
                }
                TurnstileEvent.PASS -> {
                    turnstile.lock()
                    currentState = State.LOCKED
                }
            }
        }
    }

    enum class State {
        LOCKED, UNLOCKED
    }
}