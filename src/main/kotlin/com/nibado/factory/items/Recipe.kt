package com.nibado.factory.items

data class Recipe(val name: String, val type: String, val duration: Int, val items: Map<String, Int>)
