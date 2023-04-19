package com.fappslab.libraries.arch.testing.rules

import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import com.google.android.material.R
import org.junit.runner.Description
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

@VisibleForTesting(otherwise = Modifier.PRIVATE)
abstract class FragmentTestRule<F : Fragment>(
    private val fragmentKClass: KClass<F>,
    private val fragmentArgs: Bundle? = null,
    @StyleRes private val themeResId: Int = R.style.Theme_MaterialComponents_Light_DarkActionBar,
    private val lifecycleInitState: Lifecycle.State = Lifecycle.State.RESUMED
) : KoinTestRule() {

    private var scenario: FragmentScenario<F>? = null

    override fun finished(description: Description) {
        super.finished(description)
        cleanupTestRule()
    }

    protected fun launchFragment(fragment: (F) -> Unit) {
        scenario = FragmentScenario.launchInContainer(
            fragmentClass = fragmentKClass.java,
            fragmentArgs = fragmentArgs,
            themeResId = themeResId,
            initialState = lifecycleInitState
        ).apply {
            stateScenarioHandler(scenario = this)
        }.onFragment(fragment)
    }

    private fun stateScenarioHandler(scenario: FragmentScenario<F>) {
        if (lifecycleInitState != Lifecycle.State.RESUMED) {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }
    }

    private fun closeFragmentScenario() {
        scenario?.apply {
            moveToState(Lifecycle.State.DESTROYED)
            close()
        }
    }

    private fun cleanupTestRule() {
        closeFragmentScenario()
    }
}
