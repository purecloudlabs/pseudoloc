package com.genesys.pseudoloc.android.it

import com.genesys.pseudoloc.android.AndroidPseudolocalizer
import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringArrayResource
import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import org.assertj.core.api.Assertions
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

    @Test
    fun itShouldNotPseudolocalizeExtraExemptedSubstrings() {
        val (brand, version) = arrayOf("{{brand}}", "{{version}}")
        val input = StringResourceFile(strings = listOf(
                StringResource(name = "app_name", textContent = "$brand Version: $version")
        ))

        val actual = androidPseudolocalizer.pseudolocalize(
                inStringResourceFile = input,
                extraExemptSubstrings = arrayOf(brand, version)
        )

        Assertions.assertThat(actual.strings[0].textContent).containsSequence(brand, version)
    }

    @Test
    fun itShouldNotPseudolocalizePlatformExemptedSubstrings() {
        val (boldOpen, boldClose) = arrayOf("<b>", "</b>")
        val (italicOpen, italicClose) = arrayOf("<i>", "</i>")
        val (underlineOpen, underlineClose) = arrayOf("<u>", "</u>")
        val input = StringResourceFile(strings = listOf(
                StringResource(name = "with_bold_text", textContent = "With ${boldOpen}bold${boldClose} text"),
                StringResource(name = "with_italic_text", textContent = "With ${italicOpen}italic${italicClose} text"),
                StringResource(name = "with_underline_text", textContent = "With ${underlineOpen}underline${underlineClose} text")
        ))

        val actual = androidPseudolocalizer.pseudolocalize(input)

        Assertions.assertThat(actual.strings[0].textContent).containsSequence(boldOpen, boldClose)
        Assertions.assertThat(actual.strings[1].textContent).containsSequence(italicOpen, italicClose)
        Assertions.assertThat(actual.strings[2].textContent).containsSequence(underlineOpen, underlineClose)
    }
}