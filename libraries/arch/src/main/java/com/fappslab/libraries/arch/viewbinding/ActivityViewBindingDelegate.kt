package com.fappslab.libraries.arch.viewbinding

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<Binding : ViewBinding>(
    private val bindingClass: Class<Binding>
) : ReadOnlyProperty<AppCompatActivity, Binding>, DefaultLifecycleObserver {

    private var binding: Binding? = null

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): Binding =
        thisRef.binding()

    override fun onDestroy(owner: LifecycleOwner) {
        binding = null
        owner.lifecycle.removeObserver(this)
    }

    @Suppress("UNCHECKED_CAST")
    private fun AppCompatActivity.binding(): Binding {
        binding?.let { return it }

        lifecycle.addObserver(this@ActivityViewBindingDelegate)

        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        val invokeLayout = inflateMethod.invoke(null, layoutInflater) as Binding
        setContentView(invokeLayout.root)

        return invokeLayout.also { binding = it }
    }
}

inline fun <reified Binding : ViewBinding> AppCompatActivity.viewBinding() =
    ActivityViewBindingDelegate(Binding::class.java)
