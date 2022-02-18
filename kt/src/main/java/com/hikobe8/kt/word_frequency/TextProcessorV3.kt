package com.hikobe8.kt.word_frequency

class TextProcessorV3 {

    private fun String.clean(): String {
        return replace("[^a-zA-Z]".toRegex(), " ")
    }

    private fun List<String>.getWordCount(): Map<String, Int> {
        val map = HashMap<String, Int>()
        forEach { word ->
            if ("" != word) {
                val count = map.getOrDefault(word, 0)
                map[word] = count + 1
            }
        }
        return map
    }

    private inline fun <T> Map<String, Int>.map2List(transform: (Map.Entry<String, Int>) -> T): MutableList<T> {
        val list = mutableListOf<T>()
        entries.forEach { entry ->
            val freq = transform(entry)
            list.add(freq)
        }
        return list
    }

    fun processText(text: String): List<WordFrequency> {
        return text.clean()
            .split(" ")
            .getWordCount()
            .map2List { WordFrequency(it.key, it.value) }
            .sortedByDescending { it.count }
    }
}