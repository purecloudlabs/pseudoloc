package com.genesys.pseudoloc

class Generator {

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
    var extendedAsciiConverter = ExtendedAsciiConverter()

    internal fun generate(
            input: String,
            convertToExtendedAscii: Boolean = false,
            padToFactor: Boolean = false,
            surround: Boolean = false
    ) : String {
        var output = input
        if (convertToExtendedAscii) {
            output = extendedAsciiConverter.convert(output)
        }
        if (padToFactor) {
            val surroundConsumesPadding = if (surround) 1 else 0
            val target = Math.round(output.length * factor).toInt()
            val padFront = Math.max(
                    Math.min(
                            Math.ceil((target - output.length) / 2.0).toInt() - surroundConsumesPadding,
                            frontPaddingCharacters.size
                    ),
                    0
            )
            val padBack = Math.min(
                    (target - output.length - padFront - (2 * surroundConsumesPadding)),
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
                    (output.length + frontPadding.length + backPadding.length + (2 * surroundConsumesPadding))
            if (paddingDeficit > 0) {
                backPadding = backPadding.padStart(backPadding.length + paddingDeficit, '_')
            }
            output = "${frontPadding}${output}${backPadding}"
        }
        if (surround) {
            output = "[${output}]"
        }
        return output
    }

    fun generate(input: String) : String {
        return generate(
                input,
                convertToExtendedAscii = true,
                padToFactor = true,
                surround = true
        )
    }


}