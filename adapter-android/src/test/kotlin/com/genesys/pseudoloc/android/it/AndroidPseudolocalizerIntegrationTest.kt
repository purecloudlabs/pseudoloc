package com.genesys.pseudoloc.android.it

import com.genesys.pseudoloc.android.AndroidPseudolocalizer
import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringArrayResource
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
        val input = StringResourceFile(strings = listOf(StringResource("input_very_short", "No")))
        val expected = StringResourceFile(strings = listOf(StringResource("input_very_short", "[Nō]")))

        val actual = androidPseudolocalizer.pseudolocalize(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldPseudolocalizeWithOnlyInputShort() {
        val input = StringResourceFile(strings = listOf(StringResource("input_short", "Hello world")))
        val expected = StringResourceFile(strings = listOf(StringResource("input_short", "[‘Hēllō wŏrldя]")))

        val actual = androidPseudolocalizer.pseudolocalize(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldNotPseudolocalizeStringWithReference() {
        val input = StringResourceFile(strings = listOf(StringResource("foo", "@string/foo")))
        val expected = input.copy()

        val actual = androidPseudolocalizer.pseudolocalize(input)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldNotPseudolocalizeStringArrayItemWithReference() {
        val input = StringResourceFile(stringArrays = listOf(
                StringArrayResource(
                        name = "foos",
                        items = listOf(
                                StringArrayItemResource("@string/foo")
                        )
                )
        ))
        val expected = input.copy()

        val actual = androidPseudolocalizer.pseudolocalize(input)

        assertEquals(expected, actual)
    }
}