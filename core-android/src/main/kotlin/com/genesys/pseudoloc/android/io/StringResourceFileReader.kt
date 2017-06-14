package com.genesys.pseudoloc.android.io

import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class StringResourceFileReader {

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()

    fun read(inFile: File) : StringResourceFile {
        val document = documentBuilderFactory.newDocumentBuilder().parse(inFile)
        val stringElements = document.getElementsByTagName("string")
        val stringResources = mutableListOf<StringResource>()
        for (i in 0 .. stringElements.length - 1) {
            val stringElement = stringElements.item(i)
            val name = stringElement.attributes.getNamedItem("name")?.nodeValue!!
            stringResources += StringResource(
                    name = name,
                    textContent = stringElement.textContent
            )
        }
        return StringResourceFile(ArrayList(stringResources))
    }
}