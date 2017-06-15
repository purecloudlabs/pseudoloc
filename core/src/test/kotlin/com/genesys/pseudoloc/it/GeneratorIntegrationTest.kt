package com.genesys.pseudoloc.it

import com.genesys.pseudoloc.Generator
import com.genesys.pseudoloc.util.INPUT_VERY_SHORT
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GeneratorIntegrationTest {

    private lateinit var generator : Generator

    @Before
    fun setup() {
        generator = Generator()
    }

    @Test
    fun itShouldGenerateFromVeryShort() {
        val expected = "[Nō]"

        val actual = generator.generate(INPUT_VERY_SHORT)

        assertEquals(expected, actual)
    }

    @Test
    fun itShouldGenerateFromGenesysSlogan() {
        val expected = "[‘İ球Gēńęśyş ĩś Trũştěd by Ovēr 10,000 Cōmpåņĭęś Glŏbâlly___________яش]"
        val input = "Genesys is Trusted by Over 10,000 Companies Globally"

        val actual = generator.generate(input)

        assertEquals(expected, actual)
    }
}