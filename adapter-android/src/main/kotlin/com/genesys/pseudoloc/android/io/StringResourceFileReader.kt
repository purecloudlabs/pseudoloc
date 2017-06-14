package com.genesys.pseudoloc.android.io

import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringArrayResource
import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import org.w3c.dom.Document
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class StringResourceFileReader {

    val documentBuilderFactory = DocumentBuilderFactory.newInstance()

    fun read(inFile: File) : StringResourceFile {
        val document = documentBuilderFactory.newDocumentBuilder().parse(inFile)
        val stringResources = buildStringResources(document)
        val stringArrayResources = buildStringArrayResources(document)
        return StringResourceFile(stringResources, stringArrayResources)
    }

    internal fun buildStringResources(document: Document): List<StringResource> {
        val stringElements = document.getElementsByTagName("string")
        val stringResources = arrayListOf<StringResource>()
        for (i in 0 .. stringElements.length - 1) {
            val stringElement = stringElements.item(i)
            if (stringElement.textContent.trim().isEmpty()) continue
            val name = stringElement.attributes.getNamedItem("name")?.nodeValue!!
            stringResources += StringResource(
                    name = name,
                    textContent = stringElement.textContent
            )
        }
        return stringResources.toList()
    }

    internal fun buildStringArrayResources(document: Document): List<StringArrayResource> {
        val stringArrayElements = document.getElementsByTagName("string-array")
        val stringArrayResources = arrayListOf<StringArrayResource>()
        for (i in 0 .. stringArrayElements.length - 1) {
            val stringArrayElement = stringArrayElements.item(i)
            val name = stringArrayElement.attributes.getNamedItem("name")?.nodeValue!!
            val items = buildStringArrayItemResources(stringArrayElement)
            stringArrayResources += StringArrayResource(name, items)
        }
        return stringArrayResources.toList()
    }

    internal fun buildStringArrayItemResources(stringArrayElement: Node): List<StringArrayItemResource>  {
        val itemResources = arrayListOf<StringArrayItemResource>()
        for (i in 0 .. stringArrayElement.childNodes.length - 1) {
            val itemElement = stringArrayElement.childNodes.item(i)
            if (itemElement.textContent.trim().isEmpty()) continue
            itemResources += StringArrayItemResource(itemElement.textContent)
        }
        return itemResources.toList()
    }


}