package com.nibado.factory

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Factory {
    val queue = TaskQueue()
    private val workers = mutableListOf<Worker>()

    private val workbench = Workbench(setOf("A", "B", "C"), mutableSetOf(), queue)

    init {
        workers += Worker("W1", queue)
        workers += Worker("W2", queue)
    }

    fun workers() : List<Worker> = workers

    suspend fun start() = coroutineScope {
        workers.forEach { launch { it.work() } }
        launch {
            while(true) {
                queue += Task("Pickup A", 2000, onComplete = { workbench.add("A") })
                queue += Task("Pickup B", 1500, onComplete = { workbench.add("B") })
                queue += Task("Pickup C", 2000, onComplete = { workbench.add("C") })
                //queue += Task("Combine A+B", 4000)
                //queue += Task("Deliver AB", 2000)

                delay(10000)
            }
        }
    }

    companion object {
        val started = System.currentTimeMillis()

        fun log(line: () -> String) {
            val runningTime = System.currentTimeMillis() - started
            println("[$runningTime] ${line()}")
        }
    }
}