package com.genesys.pseudoloc.android

import com.genesys.pseudoloc.Generator
import com.genesys.pseudoloc.android.model.StringArrayItemResource
import com.genesys.pseudoloc.android.model.StringArrayResource
import com.genesys.pseudoloc.android.model.StringResource
import com.genesys.pseudoloc.android.model.StringResourceFile

class AndroidPseudolocalizer {

    private var generator = Generator()
    private var standardExemptSubstrings = arrayOf(
            "<b>", "</b>", "<i>", "</i>", "<u>", "</u>", // HTML markup natively supported by Android in strings */
            "%s", "\$s", "%d", "\$d" // Java string format parameters (consider replacing with regex if inadequate)
    )

    fun pseudolocalize(
            inStringResourceFile: StringResourceFile,
            extraExemptSubstrings: Array<String> = arrayOf()
    ): StringResourceFile {
        val exemptSubstrings = (standardExemptSubstrings + extraExemptSubstrings).distinct().toTypedArray()
        val outStringResources = arrayListOf<StringResource>()
        for (inStringResource in inStringResourceFile.strings) {
            outStringResources += if (shouldPseudolocalize(inStringResource.textContent)) {
                StringResource(
                        name = inStringResource.name,
                        textContent = generator.generate(
                                input = inStringResource.textContent,
                                exemptSubstrings = exemptSubstrings
                        )
                )
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
                    StringArrayItemResource(generator.generate(
                            input = inStringArrayItemResource.textContent,
                            exemptSubstrings = exemptSubstrings
                    ))
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
        return when  {
            inTextContent.isAndroidReference() -> false
            else -> true
        }
    }
}

private fun String.isAndroidReference(): Boolean {
    return this.startsWith('@')
}