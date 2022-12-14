package com.example.ts_state.statemachine.nestedswitchcase

import com.example.ts_state.statemachine.Turnstile
import com.example.ts_state.statemachine.TurnstileStateMachine
import com.example.ts_state.statemachine.TurnstileStateMachineTest

class NestedSwitchCaseTurnstileStateMachineTest : TurnstileStateMachineTest() {
    override fun createStateMachine(turnstile: Turnstile): TurnstileStateMachine {
        return NestedSwitchCaseTurnstileStateMachine(turnstile)
    }
}