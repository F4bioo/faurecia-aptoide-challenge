package com.fappslab.libraries.arch.testing.robot.robotview

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.fappslab.libraries.arch.testing.robot.RobotCheck

interface RobotFragmentState<V : Fragment, RC : RobotCheck<RC>, S, VM : ViewModel> {
    fun givenState(state: () -> S) = this
    fun whenLaunch(fragment: (V) -> Unit = {}): RC
}
