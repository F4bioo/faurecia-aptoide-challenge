package com.fappslab.libraries.arch.testing.robot.robotview

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.fappslab.libraries.arch.testing.robot.RobotCheck

interface RobotFragment<V : Fragment, RC : RobotCheck<RC>, S, A, VM : ViewModel> {
    fun givenState(state: () -> S) = this
    fun givenAction(invoke: VM.() -> Unit, action: () -> A) = this
    fun whenLaunch(fragment: (V) -> Unit = {}): RC
}
