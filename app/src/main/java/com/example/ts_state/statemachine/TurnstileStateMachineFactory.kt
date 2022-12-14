package com.example.ts_state.statemachine

import com.example.ts_state.TurnstileFragment
import com.example.ts_state.statemachine.nestedswitchcase.NestedSwitchCaseTurnstileStateMachine


class TurnstileStateMachineFactory {
    fun create(strategy: String, turnstile: Turnstile): TurnstileStateMachine {
        return when (strategy) {
            TurnstileFragment.NESTED_SWITCH_CASE -> NestedSwitchCaseTurnstileStateMachine(turnstile)
            else -> error("no state machine found for $strategy strategy")
        }
    }
}
