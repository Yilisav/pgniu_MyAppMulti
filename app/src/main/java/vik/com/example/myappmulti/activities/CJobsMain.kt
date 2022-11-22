package vik.com.example.myappmulti.activities

import android.Manifest
import android.app.Activity
import android.app.job.JobInfo
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import vik.com.example.myappmulti.R
import androidx.navigation.*
import androidx.navigation.ui.setupWithNavController
import vik.com.example.myappmulti.databinding.JobmainLayoutBinding
import vik.com.example.myappmulti.model.DealModel


class CJobsMain : AppCompatActivity() {
    private lateinit var binding            : JobmainLayoutBinding
    private lateinit var navController      : NavController
    private lateinit var resultLauncherAdd     : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobmainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.background = null
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

//        binding.bottomNavView.menu.getItem(1).setOnMenuItemClickListener {
//            val activityMap = Intent(this, CJobInfo::class.java)
//            resultLauncherAdd.launch(activityMap)
//            true
//        }
//        resultLauncherAdd = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent? = result.data
//                val index = data?.getIntExtra("KEY_INDEX",-1)!!.toInt()
//                val idClientLastName = data.getStringExtra("KEY_CLIENT_LAST_NAME")?:""
//                if (index < 0){
//                    //добавляем объект в список
//                    items.add(DealModel(999,"new", idClientLastName, "empty", "empty", "empty", "", "","","",""))
//                    //сообщаем адаптеру об изменениях и необходимости обновления вывода на экран
//                    binding.recyclerViewDeal.adapter?.notifyItemInserted(items.size-1)
//            }
//        }

    }
    // выход из активности с результатом отмена , для выхода из текущего пользователя
    fun exitFromJobsMain() {
        setResult(RESULT_CANCELED)
        finish()
    }

}

