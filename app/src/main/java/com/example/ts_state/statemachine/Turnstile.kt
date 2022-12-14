package com.example.ts_state.statemachine

interface Turnstile {
    fun lock()
    fun unlock()
    fun thanks()
    fun alarm()
}
