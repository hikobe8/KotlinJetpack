package com.hikobe8.kt.word_frequency

import java.io.File

class TextProcessorV1 {
    fun processText(text: String): List<WordFrequency> {
        val cleanText = clean(text)
        val words = cleanText.split(" ")
        val countMap = getWordCount(words)
        return sortByFrequency(countMap)
    }

    private fun sortByFrequency(countMap: MutableMap<String, Int>): List<WordFrequency> {
        val list = ArrayList<WordFrequency>()
        countMap.forEach { (word, count) ->
            val wordFrequency = WordFrequency(word, count)
            list.add(wordFrequency)
        }
        return list.sortedByDescending {
            it.count
        }
    }

    private fun getWordCount(words: List<String>): MutableMap<String, Int> {
        val map = HashMap<String, Int>()
        words.forEach { word ->
            if ("" != word) {
                val count = map.getOrDefault(word, 0)
                map[word] = count + 1
            }
        }
        return map
    }

    private fun clean(text: String): String {
        return text.replace("[^a-zA-Z]".toRegex(), " ")
    }
}

data class WordFrequency(val word: String, val count: Int)

fun TextProcessorV1.processFile(file: File): List<WordFrequency> {
    val text = file.readText(Charsets.UTF_8)
    return processText(text)
}