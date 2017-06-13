package com.genesys.pseudoloc

class StringPadder {

    var factor = 1.35
    var frontPaddingCharacters = listOf(
            '‘',
            'İ',
            '球'
    )
    var backPaddingCharacters = listOf(
            'я',
            'ش'
    )

    fun pad(input: String, willSurround: Boolean = true) : String {
        val surroundConsumesPadding = if (willSurround) 1 else 0
        val target = Math.round(input.length * factor).toInt()
        val padFront = Math.max(
                Math.min(
                        Math.ceil((target - input.length) / 2.0).toInt() - surroundConsumesPadding,
                        frontPaddingCharacters.size
                ),
                0
        )
        val padBack = Math.min(
                (target - input.length - padFront - (2 * surroundConsumesPadding)),
                backPaddingCharacters.size
        )
        val frontPaddingCharacterIterator = frontPaddingCharacters.iterator()
        var frontPadding = ""
        for (i in 1 .. padFront) {
            frontPadding += frontPaddingCharacterIterator.next()
        }
        val backPaddingCharacterIterator = backPaddingCharacters.iterator()
        var backPadding = ""
        for (i in 1 .. padBack) {
            backPadding += backPaddingCharacterIterator.next()
        }
        val paddingDeficit = target -
                (input.length + frontPadding.length + backPadding.length + (2 * surroundConsumesPadding))
        if (paddingDeficit > 0) {
            backPadding = backPadding.padStart(backPadding.length + paddingDeficit, '_')
        }
        return "${frontPadding}${input}${backPadding}"
    }
}