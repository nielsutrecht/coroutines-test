package com.nibado.antroutines.ui

import com.nibado.antroutines.AntState
import com.nibado.antroutines.Config
import com.nibado.antroutines.World
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.math.cos
import kotlin.math.sin

class AntUi(private val config: Config) {
    private val frame = JFrame("Ant UI")
    private val world = World.create(config)
    private val panel = AntPanel(world)

    private val timer = Timer(1000 / 25) {
        world.tick()
        panel.repaint()
    }

    fun run() {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(800, 600)
        panel.preferredSize = frame.size
        panel.background = Color.BLACK
        frame.add(panel)
        frame.pack()
        frame.isVisible = true

        timer.start()
    }

    private class AntPanel(val world: World) : JPanel() {
        override fun paintComponent(g: Graphics) {
            val g = g as Graphics2D
            super.paintComponent(g)
            g.color = Color.WHITE

            world.ants.forEach { drawAnt(it.state, g) }
        }

        private inline fun drawAnt(state: AntState, g: Graphics2D) {
            val antX = (this.width * state.x).toInt()
            val antY = (this.height * state.y).toInt()
            val x = (cos(state.angle) * 10).toInt()
            val y = (sin(state.angle) * 10).toInt()

            g.color = Color.WHITE
            g.drawLine(antX, antY, antX + x, antY + y)

            g.color = if(state.hasFood) Color.GREEN else Color.WHITE
            g.fillOval(antX - (ANT_SIZE / 2), antY - (ANT_SIZE / 2), ANT_SIZE, ANT_SIZE)

        }
    }

    companion object {
        private const val ANT_SIZE = 6
    }
}