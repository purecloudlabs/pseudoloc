package com.genesys.pseudoloc.android.it

import com.genesys.pseudoloc.android.AndroidPseudolocalizer
import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class AndroidPseudolocalizerIntegrationTest {

    private lateinit var androidPseudolocalizer : AndroidPseudolocalizer

    @Before
    fun setup() {
        androidPseudolocalizer = AndroidPseudolocalizer()
    }

    @Test
    fun itShouldPseudolocalizeWithOnlyInputVeryShort() {
        val input = StringResourceFile(listOf(StringResource("input_very_short", "No")))
        val expected = StringResourceFile(listOf(StringResource("input_very_short", "[Nō]")))

        val actual = androidPseudolocalizer.pseudolocalize(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPseudolocalizeWithOnlyInputShort() {
        val input = StringResourceFile(listOf(StringResource("input_short", "Hello world")))
        val expected = StringResourceFile(listOf(StringResource("input_short", "[‘Hēllō wŏrldя]")))

        val actual = androidPseudolocalizer.pseudolocalize(input)

        assertEquals(expected, actual)
    }
}