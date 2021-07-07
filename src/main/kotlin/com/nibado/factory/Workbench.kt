package com.nibado.factory

class Workbench(val recipe: Set<String>, val storage: MutableSet<String>, private val queue: TaskQueue) {
    fun add(part: String) {
        storage += part

        if(recipe.all { storage.contains(it) }) {
            queue += Task(
                "Combine $recipe",
                4000,
                { storage -= recipe},
                {
                    queue += Task("Deliver $recipe", 2000)
                })
        }
    }
}