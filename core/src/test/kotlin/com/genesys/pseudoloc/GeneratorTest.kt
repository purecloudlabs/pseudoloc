package com.genesys.pseudoloc

import com.genesys.pseudoloc.util.INPUT_STANDARD
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class GeneratorTest {

    lateinit var extendedAsciiConverter : ExtendedAsciiConverter
    lateinit var stringPadder: StringPadder
    lateinit var stringSurrounder : StringSurrounder

    lateinit var generator : Generator

    @Before
    fun setup() {
        extendedAsciiConverter = Mockito.mock(ExtendedAsciiConverter::class.java)
        stringPadder = Mockito.mock(StringPadder::class.java)
        stringSurrounder = Mockito.mock(StringSurrounder::class.java)
        generator = Generator()
        generator.extendedAsciiConverter = extendedAsciiConverter
        generator.stringPadder = stringPadder
        generator.stringSurrounder = stringSurrounder
    }

    @Test
    fun whenSurroundAndPad() {
        val padded = "padded"
        val surrounded = "surrounded"
        Mockito.`when`(stringPadder.pad(INPUT_STANDARD, true)).thenReturn(padded)
        Mockito.`when`(stringSurrounder.surround(padded)).thenReturn(surrounded)

        val actual = generator.generate(INPUT_STANDARD, padToFactor = true, surround = true)

        Mockito.verifyZeroInteractions(extendedAsciiConverter)
        Mockito.verify(stringPadder).pad(INPUT_STANDARD, true)
        Mockito.verify(stringSurrounder).surround(padded)
        Assertions.assertThat(actual).isSameAs(surrounded)
    }

    @Test
    fun whenConvertAndPad() {
        val converted = "converted"
        val padded = "padded"
        Mockito.`when`(extendedAsciiConverter.convert(INPUT_STANDARD)).thenReturn(converted)
        Mockito.`when`(stringPadder.pad(converted, false)).thenReturn(padded)

        val actual = generator.generate(INPUT_STANDARD, convertToExtendedAscii = true, padToFactor = true)

        Mockito.verify(extendedAsciiConverter).convert(INPUT_STANDARD)
        Mockito.verify(stringPadder).pad(converted, false)
        Assertions.assertThat(actual).isSameAs(padded)
    }

}
