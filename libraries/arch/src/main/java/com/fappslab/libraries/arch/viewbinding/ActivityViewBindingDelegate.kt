package com.fappslab.libraries.arch.viewbinding

import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<Binding : ViewBinding>(
    private val bindingClass: Class<Binding>
) : ReadOnlyProperty<ComponentActivity, Binding>, DefaultLifecycleObserver {

    private var binding: Binding? = null

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): Binding =
        thisRef.binding()

    override fun onDestroy(owner: LifecycleOwner) {
        binding = null
        super.onDestroy(owner)
    }

    @Suppress("UNCHECKED_CAST")
    private fun ComponentActivity.binding(): Binding {
        binding?.let { return it }

        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        val invokeLayout = inflateMethod.invoke(null, layoutInflater) as Binding

        setContentView(invokeLayout.root)

        return invokeLayout.also { binding = it }
    }
}

inline fun <reified Binding : ViewBinding> AppCompatActivity.viewBinding() =
    ActivityViewBindingDelegate(Binding::class.java)
