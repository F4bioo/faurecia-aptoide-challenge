package com.fappslab.libraries.arch.koinload

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

interface KoinQualifier : Qualifier {

    override val value: QualifierValue
        get() = this::class.java.name
}
