package com.nibado.factory.ui

import java.awt.Graphics2D

interface Component {
    val width: Int
    val height: Int
    val visible: Boolean

    fun draw(g: Graphics2D)
}