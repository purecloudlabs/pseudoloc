package com.genesys.pseudoloc.android.io

import com.genesys.pseudoloc.android.model.StringResourceFile
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class StringResourceFileWriter {

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val transformerFactory = TransformerFactory.newInstance()

    fun write(outStringResourceFile: StringResourceFile, outFile: File) {
        // assemble document
        val documentBuilder = documentBuilderFactory.newDocumentBuilder()
        val document = documentBuilder.newDocument()
        val resourcesElement = document.createElement("resources")
        for (stringResource in outStringResourceFile.strings) {
            val stringElement = document.createElement("string")
            stringElement.setAttribute("name", stringResource.name)
            stringElement.textContent = stringResource.textContent
            resourcesElement.appendChild(stringElement)
        }
        document.appendChild(resourcesElement)

        // write content to file
        val transformer = transformerFactory.newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        val source = DOMSource(document)
        val result = StreamResult(outFile)
        transformer.transform(source, result)
    }
}