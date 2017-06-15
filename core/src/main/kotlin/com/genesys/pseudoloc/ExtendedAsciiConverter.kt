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
        // convert
        var output = StringBuilder()
        for (c in input.toCharArray()) {
            if (next.containsKey(c)) {
                val char = next[c]!!
                output.append(char)
                next[c] = selectNextChar(c, char)
            } else {
                output.append(c)
            }
        }
        return output.toString()
    }

    private fun assembleNext(): MutableMap<Char, Char> {
        return lookup.keys.map { it to lookup.get(it)!![0] }.toMap().toMutableMap()
    }

    private fun selectNextChar(inChar: Char, previous: Char): Char {
        val chars = lookup.get(inChar)!!
        val previousIndex = chars.indexOf(previous)
        return when(previousIndex) {
            in 0 .. chars.lastIndex - 1 -> chars[previousIndex + 1]
            else -> chars[0]
        }
    }
}