package com.fappslab.libraries.arch.testing.robot.robotview

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.fappslab.libraries.arch.testing.robot.RobotCheck

interface RobotActivityAction<V : Activity, RC : RobotCheck<RC>, A, VM : ViewModel> {
    fun givenAction(invoke: VM.() -> Unit, action: () -> A) = this
    fun whenLaunch(fragment: (V) -> Unit = {}): RC
}
