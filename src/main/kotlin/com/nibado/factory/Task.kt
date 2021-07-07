package com.nibado.factory

data class Task(val name: String, val duration: Long, val onStart: () -> Unit = {}, val onComplete: () -> Unit = {}) {
    companion object {
        val IDLE = Task("Idle", 1000)
    }
}
