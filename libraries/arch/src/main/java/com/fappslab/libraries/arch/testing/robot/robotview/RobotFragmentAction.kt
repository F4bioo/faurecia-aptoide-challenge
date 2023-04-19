package com.fappslab.libraries.arch.testing.robot.robotview

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.fappslab.libraries.arch.testing.robot.RobotCheck

interface RobotFragmentAction<V : Fragment, RC : RobotCheck<RC>, A, VM : ViewModel> {
    fun givenAction(invoke: VM.() -> Unit, action: () -> A) = this
    fun whenLaunch(fragment: (V) -> Unit = {}): RC
}
