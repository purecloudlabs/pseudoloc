package com.genesys.pseudoloc.android.io

import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringArrayResource
import com.genesys.pseudoloc.android.model.StringResource
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import java.io.File

class StringResourceFileReaderTest {

    private lateinit var stringResourceFileReader : StringResourceFileReader

    @Before
    fun setup() {
        stringResourceFileReader = StringResourceFileReader()
    }

    @Test
    fun itShouldReadStrings() {
        val inFile = File(javaClass.classLoader.getResource("strings.xml").file)

        val actual = stringResourceFileReader.read(inFile)

        Assertions.assertThat(actual.strings)
                .containsExactly( // TODO: extract to test-utils module
                        StringResource("input_very_short", "No"),
                        StringResource("input_short", "Hello world"),
                        StringResource("input_standard", "I am the walrus"),
                        StringResource("input_long", "The quick brown fox jumps over the lazy dog")
                )
    }

    @Test
    fun itShouldReadStringArray() {
        val inFile = File(javaClass.classLoader.getResource("strings.xml").file)

        val actual = stringResourceFileReader.read(inFile)

        Assertions.assertThat(actual.stringArrays).hasSize(1)
        val actualStringArray = actual.stringArrays[0]
        Assertions.assertThat(actualStringArray).returns("input_array_simple", StringArrayResource::name)
        Assertions.assertThat(actualStringArray.items)
                .hasSize(4)
                .containsExactly(
                        StringArrayItemResource("No"),
                        StringArrayItemResource("Hello world"),
                        StringArrayItemResource("I am the walrus"),
                        StringArrayItemResource("The quick brown fox jumps over the lazy dog")
                )
    }
}