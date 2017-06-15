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

    fun convert(input: String) : String {
        val next = assembleNext()
        var output = StringBuilder()
        for (c in input.toCharArray()) {
            if (lookup.containsKey(c)) {
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

    private fun selectNextCharIndex(inChar: Char, currentCharIndex: Int): Int {
        val chars = lookup.get(inChar)!!
        return when(currentCharIndex) {
            in 0 .. chars.lastIndex - 1 -> currentCharIndex + 1
            else -> 0
        }
    }
}