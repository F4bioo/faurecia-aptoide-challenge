package com.fappslab.libraries.arch.testing.rules

import android.app.Activity
import android.content.Intent
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.Description
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

@VisibleForTesting(otherwise = Modifier.PRIVATE)
abstract class ActivityTestRule<A : Activity>(
    private val activityKClass: KClass<A>,
    private val activityIntent: Intent? = null,
    private val lifecycleInitState: Lifecycle.State = Lifecycle.State.RESUMED
) : KoinTestRule() {

    private var scenario: ActivityScenario<A>? = null

    override fun finished(description: Description) {
        super.finished(description)
        cleanupTestRule()
    }

    protected fun launchActivity(activity: (A) -> Unit) {
        scenario = createScenario()
            .apply {
                stateScenarioHandler(scenario = this)
            }.onActivity(activity)
    }

    private fun createScenario(): ActivityScenario<A> {
        val intent = activityIntent ?: Intent(
            ApplicationProvider.getApplicationContext(),
            activityKClass.java
        )

        return ActivityScenario.launch(intent)
    }

    private fun stateScenarioHandler(scenario: ActivityScenario<A>) {
        if (lifecycleInitState != Lifecycle.State.RESUMED) {
            scenario.moveToState(Lifecycle.State.RESUMED)
        }
    }

    private fun closeActivityScenario() {
        scenario?.apply {
            moveToState(Lifecycle.State.DESTROYED)
            close()
        }
    }

    private fun cleanupTestRule() {
        closeActivityScenario()
    }
}
