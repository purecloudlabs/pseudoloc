package com.genesys.pseudoloc.cli.android

import com.genesys.pseudoloc.android.AndroidPseudolocalizer
import com.genesys.pseudoloc.android.io.StringResourceFileReader
import com.genesys.pseudoloc.android.io.StringResourceFileWriter
import com.github.rvesse.airline.annotations.Arguments
import com.github.rvesse.airline.annotations.Command
import com.github.rvesse.airline.annotations.Option
import com.github.rvesse.airline.annotations.restrictions.Required
import java.io.File

@Command(
        name = "generate-strings-xml",
        groupNames = arrayOf(ANDROID_GROUP),
        description = "Generate a pseudolocalized strings.xml"
)
class GenerateStringsXml : Runnable {

    @Arguments(
            title = arrayOf("SOURCE", "DESTINATION"),
            description = """
Specify the path of the original strings.xml (SOURCE) to read, and the path where to write the pseudolocalized
strings.xml (DESTINATION).
"""
    )
    @Required
    lateinit var args: List<String>

    @Option(
            name = arrayOf("--exempt-substring"),
            title = "exempt substring",
            description = """
Specify any additional substrings to exempt beyond those required by the Android platform (we'll
take care of those for you).
"""
    )
    var exemptSubstrings = arrayListOf<String>()

    internal val reader = StringResourceFileReader()
    internal val writer = StringResourceFileWriter()
    internal val pseudolocalizer = AndroidPseudolocalizer()

    override fun run() {
        val source = File(args[0])
        val destination = File(args[1])
        val input = reader.read(source)
        val output = pseudolocalizer.pseudolocalize(input, exemptSubstrings.toTypedArray())
        writer.write(output, destination)
    }
}