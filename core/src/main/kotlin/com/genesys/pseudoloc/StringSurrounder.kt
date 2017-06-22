package com.genesys.pseudoloc

class StringSurrounder {

    fun surround(input: String, surroundChar: Pair<Char, Char> = '[' to ']') : String {
        return "${surroundChar.first}$input${surroundChar.second}"
    }
}