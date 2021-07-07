package com.nibado.factory.ui.components

import com.nibado.factory.Worker
import com.nibado.factory.ui.Component
import java.awt.Color
import java.awt.Graphics2D

class WorkerComponent(val worker: Worker) : Component {
    override val width = 200
    override val height = 25
    override val visible = true

    override fun draw(g: Graphics2D) {
        g.color = Color.WHITE
        val status = "${worker.name}: ${worker.currentAction.name} (${worker.currentAction.percentage}%)"
        g.drawString(status, 0, 20)
    }
}