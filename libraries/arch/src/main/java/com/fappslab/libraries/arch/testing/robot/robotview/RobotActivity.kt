package com.fappslab.libraries.arch.testing.robot.robotview

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.fappslab.libraries.arch.testing.robot.RobotCheck

interface RobotActivity<V : Activity, RC : RobotCheck<RC>, S, A, VM : ViewModel> {
    fun givenState(state: () -> S) = this
    fun givenAction(invoke: VM.() -> Unit, action: () -> A) = this
    fun whenLaunch(activity: (V) -> Unit = {}): RC
}
