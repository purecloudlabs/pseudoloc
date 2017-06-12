package com.genesys.pseudoloc

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class GeneratorTest {

    lateinit var extendedAsciiConverter : ExtendedAsciiConverter

    lateinit var generator : Generator
    private val inputVeryShort = "No"
    private val inputShort = "Hello world"
    private val inputStandard = "I am the walrus"
    private val inputLong = "The quick brown fox jumps over the lazy dog"

    @Before
    fun setup() {
        extendedAsciiConverter = Mockito.mock(ExtendedAsciiConverter::class.java)
        generator = Generator()
        generator.extendedAsciiConverter = extendedAsciiConverter
    }

    @Test
    fun itShouldSurround() {
        val expected = "[I am the walrus]"

        val actual = generator.generate(inputStandard, surround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputVeryShortToFactor() {
        val expected = "‘${inputVeryShort}"

        val actual = generator.generate(inputVeryShort, padToFactor = true)

        assertEquals(expected, actual)
    }

    @Test
    fun whenSurroundAndPadToFactorVeryShortInput() {
        val expected = "[${inputVeryShort}]"

        val actual = generator.generate(inputVeryShort, padToFactor = true, surround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputShortToFactor() {
        val expected = "‘İ${inputShort}яش"

        val actual = generator.generate(inputShort, padToFactor = true)

        assertEquals(expected, actual)
    }

    @Test
    fun whenSurroundAndPadToFactorShortInput() {
        val expected = "[‘${inputShort}я]"

        val actual = generator.generate(inputShort, padToFactor = true, surround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputStandardToFactor() {
        val expected = "‘İ球${inputStandard}яش"

        val actual = generator.generate(inputStandard, padToFactor = true)

        assertEquals(expected, actual)
    }

    @Test
    fun whenSurroundAndPadToFactorStandardInput() {
        val expected = "[‘İ${inputStandard}я]"

        val actual = generator.generate(inputStandard, padToFactor = true, surround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputLongToFactor() {
        val expected = "‘İ球The quick brown fox jumps over the lazy dog__________яش"

        val actual = generator.generate(inputLong, padToFactor = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldSurroundAndPadInputLongToFactor() {
        val expected = "[‘İ球The quick brown fox jumps over the lazy dog________яش]"

        val actual = generator.generate(inputLong, padToFactor = true, surround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvert() {
        val mockOutput = "mock output"
        Mockito.`when`(extendedAsciiConverter.convert(inputStandard)).thenReturn(mockOutput)

        val actual = generator.generate(inputStandard, convertToExtendedAscii = true)

        Mockito.verify(extendedAsciiConverter).convert(inputStandard)
        Assertions.assertThat(actual).isSameAs(mockOutput)
    }

}
