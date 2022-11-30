package vik.com.example.myappmulti.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.*
import androidx.navigation.ui.setupWithNavController
import com.yandex.mapkit.MapKitFactory
import vik.com.example.myappmulti.CApplication
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.JobmainLayoutBinding
import vik.com.example.myappmulti.viewmodels.CViewModelActivityList
import vik.com.example.myappmulti.viewmodels.CViewModelFactory


class CJobsMain : AppCompatActivity() {
    private lateinit var binding            : JobmainLayoutBinding
    private lateinit var navController      : NavController
    private lateinit var resultLauncher     : ActivityResultLauncher<Intent>

//    private val viewModelActivityList: CViewModelActivityList by viewModels {
//        CViewModelFactory((application as CApplication).repositoryDeals)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//
//        // Активация яндекс карт по ключу
//        MapKitFactory.setApiKey("340e1f06-59b9-4e22-bd0f-a6c9903da439")


        binding = JobmainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // фон нижней панели навигации невидимый
        binding.bottomNavView.background = null
        // 1 позиция в меню нижней панели навигации неактивная (в меню 3 позиции: 0, 1, 2)
        binding.bottomNavView.menu.getItem(1).isEnabled = false

        // навигация на нижней панели навигации
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)




        /************************************************************/
        /** выход из ативности кнопкой назад без смены пользователя */
        /************************************************************/
        onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    setResult(RESULT_OK)
                    finish()
                }
            }
        )

        // обработка при возврате из активити
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //val data: Intent? = result.data
                println("ok")
            }
        }
    }
    // выход из активности с результатом отмена , для выхода из текущего пользователя
    fun exitFromJobsMain() {
        setResult(RESULT_CANCELED)
        finish()
    }
    /****************************/
    /** верхнее меню в заголовке*/
    /****************************/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_other_activity, menu)
        return true
    }
    // обработка кнопок меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {

            R.id.mCalculator -> {
                val activityCalculator = Intent(this, CCalculator::class.java)
                resultLauncher.launch(activityCalculator)
                true
            }
            R.id.mAbout -> {
                val activityAbout = Intent(this, CAbout::class.java)
                resultLauncher.launch(activityAbout)
                true
            }
            R.id.mExit -> {
                // выход из активности с результатом отмена , для выхода из текущего пользователя
                setResult(RESULT_CANCELED)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

