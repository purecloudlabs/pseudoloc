package com.genesys.pseudoloc.android.io

import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class StringResourceFileWriterTest {

    @Rule @JvmField
    val tempFolder = TemporaryFolder()

    private lateinit var stringResourceFileWriter : StringResourceFileWriter

    @Before
    fun setup() {
        stringResourceFileWriter = StringResourceFileWriter()
    }

    @Test
    fun itShouldWriteStrings() {
        val outFile = tempFolder.newFile("itShouldWriteStrings.txt")
        // not necessary for sake of this test to pass pseudolocalized strings
        val outStringResourceFile = StringResourceFile(listOf(
                StringResource("input_very_short", "No"),
                StringResource("input_short", "Hello world"),
                StringResource("input_standard", "I am the walrus"),
                StringResource("input_long", "The quick brown fox jumps over the lazy dog")
        ))
        val expected = """
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<resources>
    <string name="input_very_short">No</string>
    <string name="input_short">Hello world</string>
    <string name="input_standard">I am the walrus</string>
    <string name="input_long">The quick brown fox jumps over the lazy dog</string>
</resources>
""".trim()

        stringResourceFileWriter.write(outStringResourceFile, outFile)

        Assertions.assertThat(outFile)
                .exists()
                .hasContent(expected)
    }
}