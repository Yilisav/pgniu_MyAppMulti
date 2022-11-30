package vik.com.example.myappmulti.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import vik.com.example.myappmulti.CApplication
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.contrakt.HasCustomTitle
import vik.com.example.myappmulti.databinding.JobinfoLayoutBinding
import vik.com.example.myappmulti.screens.CJobListNavigator
import vik.com.example.myappmulti.viewmodels.CViewModelActivityList
import vik.com.example.myappmulti.viewmodels.CViewModelDealInfo
import vik.com.example.myappmulti.viewmodels.CViewModelFactory
import java.util.*
import kotlin.properties.Delegates.notNull


class CJobInfo : AppCompatActivity(), HasCustomTitle {

    private lateinit var binding: JobinfoLayoutBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var idIndex by notNull<Int>()
    private lateinit var idClientLastName : String
    private var id : UUID? = null

    private val viewModelInfo: CViewModelDealInfo by viewModels {
        CViewModelFactory((application as CApplication).repositoryDeals)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobinfoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //val data: Intent? = result.data
                println("ok")
            }
        }

        intent.extras?.let{
            val id = UUID.fromString(it.getString("KEY_DEAL_ID"))
            //val idIndex = it.getInt("KEY_INDEX")
            //idClientLastName = it.getString("KEY_CLIENT_LAST_NAME")?:""

            // Create a new coroutine in the lifecycleScope
            lifecycleScope.launch {
                // repeatOnLifecycle launches the block in a new coroutine every time the
                // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    // Trigger the flow and start listening for values.
                    // This happens when lifecycle is STARTED and stops
                    // collecting when the lifecycle is STOPPED
                    //viewModelInfo.idIndex = idIndex
                    // прогружаем  объект из БД
                    viewModelInfo.setId(id)

                    // выводим данные объекта на экран

                    binding.tiIdService.editText?.setText(viewModelInfo.item?.idService.toString()) ?: ""
                    binding.tiServiceStatus.editText?.setText(viewModelInfo.item?.serviceStatus) ?: ""
                    binding.tiClientLastName.editText?.setText(viewModelInfo.item?.clientLastName) ?: ""
                    binding.tiClientFirstName.editText?.setText(viewModelInfo.item?.clientFirstName) ?: ""
                    binding.tiService.editText?.setText(viewModelInfo.item?.service) ?: ""
                    binding.tiServiceCategory.editText?.setText(viewModelInfo.item?.serviceCategory) ?: ""
                    binding.tiServiceDescription.editText?.setText(viewModelInfo.item?.serviceDescription) ?: ""
                    binding.tiDateInCome.editText?.setText(viewModelInfo.item?.dateInCome) ?: ""
                    binding.tiDateExecution.editText?.setText(viewModelInfo.item?.dateExecution) ?: ""
                    binding.tiEmployeeLastName.editText?.setText(viewModelInfo.item?.employeeLastName) ?: ""
                    binding.tiEmployeeFirstName.editText?.setText(viewModelInfo.item?.employeeFirstName) ?: ""
                    binding.tiAddress.editText?.setText(viewModelInfo.item?.address) ?: ""
                    binding.tiLatitude.editText?.setText(viewModelInfo.item?.latitude.toString()) ?: ""
                    binding.tiLongitude.editText?.setText(viewModelInfo.item?.longitude.toString()) ?: ""

                }
            }

//            // вывод в форму JobInfo
//            binding.tiClientLastName.editText?.setText(idClientLastName)
//            binding.tiIdService.editText?.setText (idIndex.toString())

        }?:run{
            lifecycleScope.launch {
                viewModelInfo.idIndex = -1
                viewModelInfo.setId(null)
                binding.tiClientLastName.editText?.setText("")
            }
        }




        /***************************************************/
        /** выход из ативности кнопкой назад с сохранением */
        /***************************************************/
        onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    exitActivity()
                }
            }
        )
        /*********************************************/
        /** выход из ативности кнопкой с сохранением */
        /*********************************************/
        binding.close.setOnClickListener{
            exitActivity()
        }

    }
    /*********************************************/
    /** выход из ативности обработка процесса    */
    /*********************************************/
    private fun exitActivity() {
        //val intent = Intent(this, CJobListNavigator::class.java)
        //intent.putExtra("KEY_INDEX", viewModelInfo.idIndex)
        //intent.putExtra("NEW_NAME", binding.tiClientLastName.editText?.text.toString())

        viewModelInfo.save(
            binding.tiIdService.editText?.text.toString().toInt(),
            binding.tiServiceStatus.editText?.text.toString(),
            binding.tiClientLastName.editText?.text.toString(),
            binding.tiClientFirstName.editText?.text.toString(),
            binding.tiService.editText?.text.toString(),
            binding.tiServiceCategory.editText?.text.toString(),
            binding.tiServiceDescription.editText?.text.toString(),
            binding.tiDateInCome.editText?.text.toString(),
            binding.tiDateExecution.editText?.text.toString(),
            binding.tiEmployeeLastName.editText?.text.toString(),
            binding.tiEmployeeFirstName.editText?.text.toString(),
            binding.tiAddress.editText?.text.toString(),
            binding.tiLatitude.editText?.text.toString().toDouble(),
            binding.tiLongitude.editText?.text.toString().toDouble(),
        )

        setResult(RESULT_OK)//, intent)
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

    override fun getTitleRes(): Int = R.string.Info
}