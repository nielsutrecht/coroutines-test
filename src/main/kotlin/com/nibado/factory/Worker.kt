package com.nibado.factory

import kotlinx.coroutines.delay

class Worker(val name: String, val queue: TaskQueue) {
    private var current: Action = TaskAction(Task("Startup", 0), 0, 0)
    private var work = true

    val currentAction: Action
        get() = current

    suspend fun work() {
        while (work) {
            val task = queue.take()
            current = toAction(task)
            delay(maxOf(currentAction.finish - System.currentTimeMillis(), 0L))
            Factory.log { "$name finished ${currentAction.name}" }
            current.complete()
        }
    }

    private fun toAction(task: Task): Action {
        task.onStart()
        return TaskAction(task, System.currentTimeMillis(), System.currentTimeMillis() + task.duration)
    }

    override fun toString(): String = "Worker($name, $currentAction)"
}