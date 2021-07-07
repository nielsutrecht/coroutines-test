package com.nibado.factory.items

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object RecipeReader {
    fun read() : List<Recipe> {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

        return RecipeReader::class.java.getResourceAsStream("/recipes.yml").use {
            mapper.readValue<RecipeList>(it).recipes.map {
                Recipe(it.key, it.value.type, it.value.duration, it.value.items)
            }
        }
    }

    private data class ListItem(val type: String, val duration: Int, val items: Map<String, Int>)
    private data class RecipeList(val recipes: Map<String, ListItem>)
}

fun main() {
    RecipeReader.read().forEach { println(it) }
}