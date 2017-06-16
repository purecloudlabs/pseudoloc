package com.genesys.pseudoloc

import org.assertj.core.api.Assertions
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals

class ExtendedAsciiConverterTest {

    private val converter = ExtendedAsciiConverter()

    @Test
    fun itShouldConvertARepeating() {
        val input = "aaaaa"
        val expected = "åâáàæ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertCRepeating() {
        val input = "cc"
        val expected = "ćĉ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertERepeating() {
        val input = "eee"
        val expected = "ēęě"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertGRepeating() {
        val input = "gg"
        val expected = "ĝğ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertIRepeating() {
        val input = "iii"
        val expected = "ĩĭȋ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertNRepeating() {
        val input = "nnn"
        val expected = "ńņň"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertORepeating() {
        val input = "oooo"
        val expected = "ōŏőȭ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertSRepeating() {
        val input = "ss"
        val expected = "śş"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertURepeating() {
        val input = "uuu"
        val expected = "ũūů"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldConvertZRepeating() {
        val input = "zz"
        val expected = "źž"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldWrapCharIterator() {
        val input = "zzz"
        val expected = "źžź"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldNotConvertExemptedSubstrings() {
        val input = "The quick {{colour}} fox jumps over the {{adjective}} dog"
        val exemptions = arrayOf("{{adjective}}", "{{colour}}")
        val expected = "Thē qũĩćk {{colour}} fōx jūmpś ŏvęr thě {{adjective}} dőĝ"

        val actual = converter.convert(input, exemptions)

        assertEquals(expected, actual)
    }
}
