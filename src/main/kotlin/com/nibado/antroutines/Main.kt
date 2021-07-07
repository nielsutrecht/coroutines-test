package com.nibado.antroutines

import com.nibado.antroutines.ui.AntUi
import java.io.File

fun main(args: Array<String>) {
    val config = if(args.isEmpty()) {
        Config.loadDefault()
    } else {
        Config.load(File(args.first()))
    }
    val ui = AntUi(config)
    ui.run()
}