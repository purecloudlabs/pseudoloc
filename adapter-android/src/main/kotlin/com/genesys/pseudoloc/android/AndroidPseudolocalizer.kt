package com.genesys.pseudoloc.android

import com.genesys.pseudoloc.Generator
import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringArrayResource
import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile
import javax.xml.soap.SAAJResult

class AndroidPseudolocalizer {

    private var generator = Generator()

    fun pseudolocalize(inStringResourceFile: StringResourceFile): StringResourceFile {
        val outStringResources = arrayListOf<StringResource>()
        for (inStringResource in inStringResourceFile.strings) {
            outStringResources += if (shouldPseudolocalize(inStringResource.textContent)) {
                StringResource(
                        name = inStringResource.name,
                        textContent = generator.generate(inStringResource.textContent))
            } else {
                StringResource(
                        name = inStringResource.name,
                        textContent = inStringResource.textContent)
            }
        }

        val outStringArrayResources = arrayListOf<StringArrayResource>()
        for (inStringArrayResource in inStringResourceFile.stringArrays) {
            val outStringArrayItemResources = arrayListOf<StringArrayItemResource>()
            for (inStringArrayItemResource in inStringArrayResource.items) {
                outStringArrayItemResources += if (shouldPseudolocalize(inStringArrayItemResource.textContent)) {
                    StringArrayItemResource(generator.generate(inStringArrayItemResource.textContent))
                } else {
                    StringArrayItemResource(inStringArrayItemResource.textContent)
                }
            }
            outStringArrayResources += StringArrayResource(
                    name = inStringArrayResource.name,
                    items = outStringArrayItemResources.toList()
            )
        }

        return StringResourceFile(outStringResources.toList(), outStringArrayResources.toList())
    }

    internal fun shouldPseudolocalize(inTextContent: String): Boolean {
        // TODO: exclusions for certain strings (references, formats, etc?)
        return true
    }
}
