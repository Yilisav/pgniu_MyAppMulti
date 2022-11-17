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
        }

        binding.tvLastNameInfo.text = idClientLastName.toString()
        binding.tvFirstNameInfo.text = idIndex.toString()



        onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val intent= Intent()
                    intent.putExtra("KEY_INDEX", idIndex )
                    intent.putExtra("NEW_NAME", binding.tfLastNameInfo.editText?.text.toString() ?: "")
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        )

        // выход из ативности
        binding.close.setOnClickListener{
            val intent= Intent()
            intent.putExtra("KEY_INDEX", idIndex )
            intent.putExtra("NEW_NAME", binding.tfLastNameInfo.editText?.text.toString() ?: "")
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    // меню
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