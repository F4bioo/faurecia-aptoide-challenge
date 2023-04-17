package com.fappslab.libraries.arch.testing.rules

import androidx.annotation.VisibleForTesting
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.reflect.Modifier

@VisibleForTesting(otherwise = Modifier.PRIVATE)
class ViewModelTestRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement =
        RuleChain
            .outerRule(SchedulersTestRule())
            .around(DispatcherTestRule())
            .apply(base, description)
}
