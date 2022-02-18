package com.hikobe8.kt.word_frequency

fun String.clean(): String {
    return replace("[^a-zA-Z]".toRegex(), " ")
}

fun List<String>.getWordCount(): Map<String, Int> {
    val map = HashMap<String, Int>()
    forEach { word ->
        if ("" != word) {
            val count = map.getOrDefault(word, 0)
            map[word] = count + 1
        }
    }
    return map
}

fun <T> Map<String, Int>.map2List(transform: (Map.Entry<String, Int>) -> T): MutableList<T> {
    val list = mutableListOf<T>()
    entries.forEach { entry ->
        val freq = transform(entry)
        list.add(freq)
    }
    return list
}

fun <T> MutableList<T>.sortByFrequency(action: (T) -> Int): MutableList<T> {
    sortByDescending {
        action(it)
    }
    return this
}

class TextProcessorV2 {
    fun processText(text: String): MutableList<WordFrequency> {
        return text.clean()
            .split(" ")
            .getWordCount()
            .map2List { WordFrequency(it.key, it.value) }
            .sortByFrequency { it.count }
    }
}