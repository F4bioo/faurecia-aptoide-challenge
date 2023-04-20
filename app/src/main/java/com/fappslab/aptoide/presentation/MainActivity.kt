package com.fappslab.aptoide.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.fappslab.aptoide.databinding.ActivityMainBinding
import com.fappslab.libraries.arch.viewbinding.viewBinding
import com.fappslab.libraries.design.extension.setStatusBarColor
import com.fappslab.libraries.navigation.HomeNavigation
import org.koin.android.ext.android.inject

private const val HOME_TAG = "HomeFragment"

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val homeNavigation: HomeNavigation by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setStatusBarColor()

        supportFragmentManager.commit {
            replace(binding.containerApp.id, homeNavigation.create(), HOME_TAG)
        }
    }
}
