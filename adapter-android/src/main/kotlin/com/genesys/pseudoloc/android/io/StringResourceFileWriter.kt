package com.genesys.pseudoloc.android.io

import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import org.w3c.dom.Document
import org.w3c.dom.Node
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
        assembleStrings(document, outStringResourceFile).forEach { resourcesElement.appendChild(it) }
        assembleStringArrays(document, outStringResourceFile).forEach { resourcesElement.appendChild(it) }
        document.appendChild(resourcesElement)

        // write content to file
        val transformer = transformerFactory.newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        val source = DOMSource(document)
        val result = StreamResult(outFile)
        transformer.transform(source, result)
    }

    fun assembleStrings(document: Document, outStringResourceFile: StringResourceFile): ArrayList<Node> {
        val stringElements = arrayListOf<Node>()
        for (stringResource in outStringResourceFile.strings) {
            val stringElement = document.createElement("string")
            stringElement.setAttribute("name", stringResource.name)
            stringElement.textContent = stringResource.textContent
            stringElements += stringElement
        }
        return stringElements
    }

    fun assembleStringArrays(document: Document, outStringResourceFile: StringResourceFile): ArrayList<Node> {
        val stringArrayElements = arrayListOf<Node>()
        for (stringArrayResource in outStringResourceFile.stringArrays) {
            val stringArrayElement = document.createElement("string-array")
            stringArrayElement.setAttribute("name", stringArrayResource.name)
            assembleStringArrayItems(document, stringArrayResource.items).forEach { stringArrayElement.appendChild(it) }
            stringArrayElements += stringArrayElement
        }
        return stringArrayElements
    }

    private fun assembleStringArrayItems(
            document: Document,
            stringArrayItemResources: List<StringArrayItemResource>
    ): ArrayList<Node> {
        val stringArrayItemElements = arrayListOf<Node>()
        for (stringArrayItemResource in stringArrayItemResources) {
            val stringArrayItemElement = document.createElement("item")
            stringArrayItemElement.textContent = stringArrayItemResource.textContent
            stringArrayItemElements += stringArrayItemElement
        }
        return stringArrayItemElements
    }
}