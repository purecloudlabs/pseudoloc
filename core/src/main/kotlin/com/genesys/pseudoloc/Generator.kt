package com.genesys.pseudoloc

class Generator {

    var extendedAsciiConverter = ExtendedAsciiConverter()
    var stringPadder = StringPadder()
    var stringSurrounder = StringSurrounder()

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
            output = stringPadder.pad(output, willSurround = surround)
        }
        if (surround) {
            output = stringSurrounder.surround(output)
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