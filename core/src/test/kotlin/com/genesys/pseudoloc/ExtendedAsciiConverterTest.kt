package com.genesys.pseudoloc

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

    fun itShouldConvertCRepeating() {
        val input = "cc"
        val expected = "ćĉ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    fun itShouldConvertERepeating() {
        val input = "eee"
        val expected = "ēęě"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

    fun itShouldConvertGRepeating() {
        val input = "gg"
        val expected = "ĝğ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
    fun itShouldConvertIRepeating() {
        val input = "iii"
        val expected = "ĩĭȋ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
    fun itShouldConvertNRepeating() {
        val input = "nnn"
        val expected = "ńņň"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
    fun itShouldConvertORepeating() {
        val input = "oooo"
        val expected = "ōŏőȭ"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
    fun itShouldConvertSRepeating() {
        val input = "ss"
        val expected = "śş"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
    fun itShouldConvertURepeating() {
        val input = "uuu"
        val expected = "ũūů"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
    fun itShouldConvertZRepeating() {
        val input = "zz"
        val expected = "źž"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }

}