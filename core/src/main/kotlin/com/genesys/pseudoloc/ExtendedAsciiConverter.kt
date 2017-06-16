package com.genesys.pseudoloc

class ExtendedAsciiConverter {

    internal val lookup = mapOf(
            'a' to listOf('å', 'â', 'á', 'à', 'æ'),
            'c' to listOf('ć', 'ĉ'),
            'e' to listOf('ē', 'ę', 'ě'),
            'g' to listOf('ĝ','ğ'),
            'i' to listOf('ĩ','ĭ','ȋ'),
            'n' to listOf('ń','ņ','ň'),
            'o' to listOf('ō','ŏ','ő','ȭ'),
            's' to listOf('ś','ş'),
            'u' to listOf('ũ','ū','ů'),
            'z' to listOf('ź','ž')
    )

    fun convert(input: String, exemptSubstrings: Array<String> = arrayOf()) : String {
        val next = assembleNext()
        val exemptRanges = assembleExemptRanges(input, exemptSubstrings)
        var output = StringBuilder()
        for ((i, c) in input.toCharArray().withIndex()) {
            if (lookup.containsKey(c) && !exemptRanges.any { it.contains(i) }) {
                val charIndex = next[c]!!
                output.append(lookup[c]!![charIndex])
                next[c] = selectNextCharIndex(c, charIndex)
            } else {
                output.append(c)
            }
        }
        return output.toString()
    }

    private fun assembleNext(): MutableMap<Char, Int> {
        return lookup.keys.map { it to 0 }.toMap().toMutableMap()
    }

    private fun assembleExemptRanges(input: String, exemptSubstrings: Array<String>): Array<IntRange> {
        val exemptRanges = arrayListOf<IntRange>()
        for (exemption in exemptSubstrings) {
            exemptRanges += input.indexRangesOfSubstring(exemption)
        }
        return exemptRanges.toTypedArray()
    }

    private fun selectNextCharIndex(inChar: Char, currentCharIndex: Int): Int {
        val chars = lookup.get(inChar)!!
        return when(currentCharIndex) {
            in 0 .. chars.lastIndex - 1 -> currentCharIndex + 1
            else -> 0
        }
    }
}

fun String.indexRangesOfSubstring(substring: String): Array<IntRange> {
    val ranges = arrayListOf<IntRange>()
    var startIndex : Int = indexOf(substring)
    var stopIndex : Int
    while (startIndex >= 0) {
        stopIndex = startIndex + substring.length
        ranges += startIndex .. stopIndex

        startIndex = indexOf(substring, stopIndex + 1)
    }
    return ranges.toTypedArray()
}