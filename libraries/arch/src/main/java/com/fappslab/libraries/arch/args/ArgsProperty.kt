package com.fappslab.libraries.arch.args

import android.os.Bundle
import android.os.Parcelable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal const val KEY_ARGS = "arch:key_args"

internal class ArgsProperty<H, P : Parcelable>(
    private val bundleResolver: (H) -> Bundle,
) : ReadOnlyProperty<H, P> {

    private var value: P? = null

    override fun getValue(thisRef: H, property: KProperty<*>): P {
        return requireNotNull((value ?: bundleResolver(thisRef).getParcelable(KEY_ARGS))) {
            "Cannot read property ${property.name} have you invoked putArguments?"
        }
    }
}
