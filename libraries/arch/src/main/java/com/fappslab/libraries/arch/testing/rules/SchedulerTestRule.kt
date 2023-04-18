package com.fappslab.libraries.arch.testing.rules

import androidx.annotation.VisibleForTesting
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.reflect.Modifier

@VisibleForTesting(otherwise = Modifier.PRIVATE)
open class SchedulerTestRule(
    open val testScheduler: Scheduler = Schedulers.trampoline()
) : TestRule {

    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { testScheduler }
                RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
                RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }

                base.evaluate()

                RxJavaPlugins.reset()
                RxAndroidPlugins.reset()
            }
        }
}
