package com.nibado.factory.ui.components

import com.nibado.factory.TaskQueue
import com.nibado.factory.ui.Component
import java.awt.Color
import java.awt.Graphics2D

class TaskQueueComponent(val queue: TaskQueue) : Component{
    override val width = 300
    override val height: Int
        get() = 25 * queue.size

    override val visible = true

    override fun draw(g: Graphics2D) {
        g.color = Color.CYAN

        queue.tasks.forEachIndexed { index, task ->
            g.drawString("${task.name} ${task.duration}", 0, 25 + 25 * index)
        }
    }
}