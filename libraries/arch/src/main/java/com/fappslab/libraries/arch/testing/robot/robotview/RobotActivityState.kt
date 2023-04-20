package com.fappslab.libraries.arch.testing.robot.robotview

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.fappslab.libraries.arch.testing.robot.RobotCheck

interface RobotActivityState<V : Activity, RC : RobotCheck<RC>, S, VM : ViewModel> {
    fun givenState(state: () -> S) = this
    fun whenLaunch(fragment: (V) -> Unit = {}): RC
}
