package com.genesys.pseudoloc

import com.genesys.pseudoloc.util.INPUT_STANDARD
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StringSurrounderTest {

    private lateinit var stringSurrounder : StringSurrounder

    @Before
    fun setup() {
        stringSurrounder = StringSurrounder()
    }

    @Test
    fun itShouldSurround() {
        val expected = "[$INPUT_STANDARD]"

        val actual = stringSurrounder.surround(INPUT_STANDARD)

        assertEquals(expected, actual)
    }
}