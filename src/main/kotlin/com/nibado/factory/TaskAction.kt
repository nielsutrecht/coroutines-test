package com.nibado.factory

class TaskAction(val task: Task, start: Long, finish: Long) : Action(task.name, start, finish) {
    override fun complete() {
        task.onComplete()
    }
}