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
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.contrakt.HasCustomTitle
import vik.com.example.myappmulti.databinding.JobinfoLayoutBinding
import vik.com.example.myappmulti.screens.CJobListNavigator
import kotlin.properties.Delegates.notNull


class CJobInfo : AppCompatActivity(), HasCustomTitle {

    private lateinit var binding: JobinfoLayoutBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var idIndex by notNull<Int>()
    private lateinit var idClientLastName : String

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
            idIndex = it.getInt("KEY_INDEX")
            idClientLastName = it.getString("KEY_CLIENT_LAST_NAME")?:""
            // вывод в форму JobInfo
            binding.tiClientLastName.editText?.setText(idClientLastName)
            binding.tiIdService.editText?.setText (idIndex.toString())
        }?:run{
            println(" No param from JobListNavigator")
            Toast.makeText(this,"Param no access", Toast.LENGTH_SHORT).show()
            idIndex = -1
            idClientLastName = ""
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
        val intent = Intent(this, CJobListNavigator::class.java)
        intent.putExtra("KEY_INDEX", idIndex)
        intent.putExtra("NEW_NAME", binding.tiClientLastName.editText?.text.toString())
        setResult(RESULT_OK, intent)
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