package com.fappslab.aptoide.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.fappslab.aptoide.databinding.ActivityMainBinding
import com.fappslab.features.home.presentation.HomeFragment
import com.fappslab.libraries.arch.viewbinding.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(binding.containerApp.id, HomeFragment.create(), "HomeFragment")
        }
    }
}
