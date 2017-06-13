package com.genesys.pseudoloc

import com.genesys.pseudoloc.util.INPUT_LONG
import com.genesys.pseudoloc.util.INPUT_SHORT
import com.genesys.pseudoloc.util.INPUT_STANDARD
import com.genesys.pseudoloc.util.INPUT_VERY_SHORT
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StringPadderTest {

    lateinit var stringPadder : StringPadder

    @Before
    fun setup() {
        stringPadder = StringPadder()
    }

    @Test
    fun itShouldPadInputVeryShortToFactorWhenWillNotSurround() {
        val expected = "‘$INPUT_VERY_SHORT"

        val actual = stringPadder.pad(INPUT_VERY_SHORT, willSurround = false)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldNotPadInputVeryShortToFactorWhenWillSurround() {
        val expected = "$INPUT_VERY_SHORT"

        val actual = stringPadder.pad(INPUT_VERY_SHORT, willSurround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputShortToFactorWhenWillNotSurround() {
        val expected = "‘İ${INPUT_SHORT}яش"

        val actual = stringPadder.pad(INPUT_SHORT, willSurround = false)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputShortToFactorWhenWillSurround() {
        val expected = "‘${INPUT_SHORT}я"

        val actual = stringPadder.pad(INPUT_SHORT, willSurround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputStandardToFactorWhenWillNotSurround() {
        val expected = "‘İ球${INPUT_STANDARD}яش"

        val actual = stringPadder.pad(INPUT_STANDARD, willSurround = false)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputStandardToFactorWhenWillSurround() {
        val expected = "‘İ${INPUT_STANDARD}я"

        val actual = stringPadder.pad(INPUT_STANDARD, willSurround = true)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputLongToFactorWhenWillNotSurround() {
        val expected = "‘İ球${INPUT_LONG}__________яش"

        val actual = stringPadder.pad(INPUT_LONG, willSurround = false)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPadInputLongToFactorWhenWillSurround() {
        val expected = "‘İ球${INPUT_LONG}________яش"

        val actual = stringPadder.pad(INPUT_LONG, willSurround = true)

        assertEquals(expected, actual)
    }
}
