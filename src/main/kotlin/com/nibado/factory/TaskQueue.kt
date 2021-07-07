package com.nibado.factory

class TaskQueue {
    private val queue = ArrayDeque<Task>()

    val tasks: List<Task>
        get() = queue
    val size: Int
        get() = queue.size

    fun take() : Task = queue.removeFirstOrNull() ?: Task.IDLE

    operator fun plusAssign(task: Task) {
        queue += task
    }
}