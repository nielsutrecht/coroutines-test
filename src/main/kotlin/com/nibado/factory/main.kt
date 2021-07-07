package com.nibado.factory

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        start()
    }
}

suspend fun start() = coroutineScope {
    val queue = TaskQueue()
    queue += Task("Pickup A", 2000)
    queue += Task("Pickup B", 1500)
    queue += Task("Combine A+B", 4000)
    queue += Task("Deliver AB", 2000)

    val worker1 = Worker("W1", queue)
    val worker2 = Worker("W2", queue)
    launch {
        worker1.work()
    }
    launch {
        worker2.work()
    }

    launch {
        while(true) {
            delay(500)
            println("W1: ${worker1.currentAction.percentage}%, W2: ${worker1.currentAction.percentage}%")
        }
    }
}

