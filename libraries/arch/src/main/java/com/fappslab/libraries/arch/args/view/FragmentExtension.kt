package com.fappslab.libraries.arch.args.view

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.fappslab.libraries.arch.args.ArgsProperty
import com.fappslab.libraries.arch.args.KEY_ARGS
import kotlin.properties.ReadOnlyProperty

fun <F : Fragment> F.withArgs(args: Bundle.() -> Unit): F = apply {
    arguments = Bundle().apply(args)
}

@Suppress("UseCheckOrError")
fun <P : Parcelable> Fragment.viewArgs(): ReadOnlyProperty<Fragment, P> = ArgsProperty { fragment ->
    fragment.arguments ?: throw IllegalStateException("Have you invoked putArguments?")
}

fun Bundle.putArguments(args: Parcelable): Unit = putParcelable(KEY_ARGS, args)
