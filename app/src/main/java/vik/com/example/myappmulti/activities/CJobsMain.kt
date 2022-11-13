package vik.com.example.myappmulti.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import vik.com.example.myappmulti.MAIN
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.JobmainBinding
import androidx.navigation.*
import vik.com.example.myappmulti.screens.job_list_nav
import vik.com.example.myappmulti.screens.map_nav


class CJobsMain : AppCompatActivity() {
    private lateinit var binding: JobmainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobmainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MAIN = this

        // инициализация фрагментов
        val ListFragment = job_list_nav()
        val MapFragment = map_nav()

        setCurrentFragment(ListFragment)

        binding.navView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.page_list-> {
                    setCurrentFragment(ListFragment)
                    true
                }
                R.id.page_map-> {
                    setCurrentFragment(MapFragment)
                    true
                }
                else -> false
            }
        }
    }
    // функция обяъвление фрагментов
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment,fragment)
            commit()
        }


}

