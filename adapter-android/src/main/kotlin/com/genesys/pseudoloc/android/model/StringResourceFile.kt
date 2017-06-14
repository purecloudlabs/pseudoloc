package com.genesys.pseudoloc.android.model

data class StringResourceFile(
        val strings: List<StringResource> = listOf(),
        val stringArrays: List<StringArrayResource> = listOf()
)