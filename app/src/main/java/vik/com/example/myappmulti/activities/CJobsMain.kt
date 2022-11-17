package vik.com.example.myappmulti.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import vik.com.example.myappmulti.R
import androidx.navigation.*
import androidx.navigation.ui.setupWithNavController
import vik.com.example.myappmulti.databinding.JobmainLayoutBinding


class CJobsMain : AppCompatActivity() {
    private lateinit var binding            : JobmainLayoutBinding
    private lateinit var resultLauncher     : ActivityResultLauncher<Intent>
    private lateinit var navController      : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobmainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // навигация на нижней панели навигации
        navController = Navigation.findNavController(this@CJobsMain, R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)



    }
}

