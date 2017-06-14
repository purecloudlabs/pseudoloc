package com.genesys.pseudoloc.android.io

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
}