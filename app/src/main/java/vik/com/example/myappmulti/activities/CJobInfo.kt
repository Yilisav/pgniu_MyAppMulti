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
import vik.com.example.myappmulti.databinding.JobinfoLayoutBinding
import vik.com.example.myappmulti.screens.CJobListNavigator


class CJobInfo : AppCompatActivity() {

    private lateinit var binding: JobinfoLayoutBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var idIndex: Int = 0
    private var idClientLastName : String = "Empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobinfoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
            }
        }

        intent.extras?.let{
            idIndex = it.getInt("KEY_INDEX")
            idClientLastName = it.getString("KEY_CLIENT_LAST_NAME")?:""
        }?:run{
            println(" No param ")
            Toast.makeText(this,"Param no access", Toast.LENGTH_SHORT).show()
            idIndex = -1
            idClientLastName = ""
        }

            binding.tvLastNameInfo.setText(idClientLastName)
            binding.tvFirstNameInfo.text = idIndex.toString()


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
        intent.putExtra("NEW_NAME", binding.tvLastNameInfo.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }

    /****************************/
    /** верхнее меню в заголовке*/
    /****************************/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
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
                setResult(RESULT_OK)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}