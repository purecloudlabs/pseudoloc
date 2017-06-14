package com.genesys.pseudoloc.android

import com.genesys.pseudoloc.Generator
import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile

class AndroidPseudolocalizer {

    private var generator = Generator()

    fun pseudolocalize(inStringResourceFile: StringResourceFile): StringResourceFile {
        val outStringResources = mutableListOf<StringResource>()
        for (inStringResource in inStringResourceFile.strings) {
            outStringResources += if (shouldPseudolocalize(inStringResource.textContent)) {
                StringResource(inStringResource.name, generator.generate(inStringResource.textContent))
            } else {
                StringResource(inStringResource.name, inStringResource.textContent)
            }
        }
        return StringResourceFile(ArrayList(outStringResources))
    }

    internal fun shouldPseudolocalize(inTextContent: String): Boolean {
        // TODO: exclusions for certain strings (references, formats, etc?)
        return true
    }
}
