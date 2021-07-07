package com.nibado.factory.ui

import com.nibado.factory.Factory
import com.nibado.factory.ui.components.TaskQueueComponent
import com.nibado.factory.ui.components.WorkerComponent
import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.Timer

class FactoryUi {
    private val frame = JFrame("Factory UI")
    private val factory = Factory()
    private val components = mutableListOf<Pair<Point, Component>>()
    private val panel = FactoryPanel(components::asSequence)

    init {
        factory.workers().forEachIndexed { index, worker ->
            val wComponent = WorkerComponent(worker)
            components += Point(0, wComponent.height * index) to wComponent
        }
        components += Point(300, 0) to TaskQueueComponent(factory.queue)
    }

    private val timer = Timer(1000 / 25) {
        panel.repaint()
    }

    fun initUi() {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(800, 600)
        panel.preferredSize = frame.size
        panel.background = Color.BLACK
        frame.add(panel)
        frame.pack()
        frame.isVisible = true

        timer.start()
    }

    suspend fun start() = factory.start()

    private class FactoryPanel(val components: () -> Sequence<Pair<Point, Component>>) : JPanel() {
        override fun paintComponent(g: Graphics) {
            val g = g as Graphics2D
            super.paintComponent(g)

            components().filter { it.second.visible }.forEach { (p, c) ->
                val (x, y) = p
                c.draw(g.create(x, y, c.width, c.height) as Graphics2D)
            }
        }
    }

    private data class Point(val x: Int, val y: Int)
}

fun main() {
    runBlocking {
        val ui = FactoryUi()
        ui.initUi()
        ui.start()
    }
}