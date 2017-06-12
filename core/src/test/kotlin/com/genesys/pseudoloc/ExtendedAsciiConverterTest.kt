package com.genesys.pseudoloc

import org.junit.Test
import kotlin.test.assertEquals

class ExtendedAsciiConverterTest {

    private val converter = ExtendedAsciiConverter()

    @Test
    fun itShouldConvertARepeating() {
        val input = "aaaa"
        val expected = "åâáà"

        val actual = converter.convert(input)

        assertEquals(expected, actual)
    }
}