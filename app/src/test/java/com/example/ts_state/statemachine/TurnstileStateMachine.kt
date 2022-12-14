package com.example.ts_state.statemachine

import org.junit.Test

import org.junit.Assert.*

abstract class TurnstileStateMachineTest {

    abstract fun createStateMachine(turnstile: Turnstile): TurnstileStateMachine

    private val turnstileSpy = TurnstileSpy()
    private val stateMachine by lazy {
        createStateMachine(turnstileSpy)
    }

    @Test
    fun `single coin`() {
        stateMachine.handleEvent(TurnstileEvent.COIN)

        assertEquals("U", turnstileSpy.interactions)
    }

    @Test
    fun `two coins`() {
        stateMachine.handleEvent(TurnstileEvent.COIN)
        stateMachine.handleEvent(TurnstileEvent.COIN)

        assertEquals("UT", turnstileSpy.interactions)
    }

    @Test
    fun `many coins`() {
        repeat(5) {
            stateMachine.handleEvent(TurnstileEvent.COIN)
        }

        assertEquals("UTTTT", turnstileSpy.interactions)
    }

    @Test
    fun `force pass`() {
        stateMachine.handleEvent(TurnstileEvent.PASS)

        assertEquals("A", turnstileSpy.interactions)
    }

    @Test
    fun `coin followed by pass`() {
        stateMachine.handleEvent(TurnstileEvent.COIN)
        stateMachine.handleEvent(TurnstileEvent.PASS)

        assertEquals("UL", turnstileSpy.interactions)
    }

    @Test
    fun `normal operation`() {
        stateMachine.handleEvent(TurnstileEvent.COIN)
        stateMachine.handleEvent(TurnstileEvent.PASS)
        stateMachine.handleEvent(TurnstileEvent.PASS)
        stateMachine.handleEvent(TurnstileEvent.COIN)
        stateMachine.handleEvent(TurnstileEvent.COIN)
        stateMachine.handleEvent(TurnstileEvent.PASS)

        assertEquals("ULAUTL", turnstileSpy.interactions)
    }

    class TurnstileSpy : Turnstile {
        var interactions = ""

        override fun lock() { interactions += "L" }
        override fun unlock() { interactions += "U" }
        override fun thanks() { interactions += "T" }
        override fun alarm() { interactions += "A" }
    }
}