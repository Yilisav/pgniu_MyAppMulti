package vik.com.example.myappmulti.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.JobinfoLayoutBinding


class CJobInfo : AppCompatActivity() {

    private lateinit var binding: JobinfoLayoutBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var idIndex: Int = -1
    private var idIdService : String = "Empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobinfoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        intent.extras?.let{
//             idIndex = it.getInt("KEY_INDEX")
//             idIdService = it.getString("KEY_ID_SERVICE")?:""
//        }?:run{
//            Toast.makeText(this,"Param no access", Toast.LENGTH_SHORT).show()
//        }
        println(" activity true ")
//        binding.tvLastNameInfo.setText (idIndex)
//        binding.tvFirstNameInfo.setText (idIndex)


        // выход из ативности
        binding.close.setOnClickListener{
            setResult(RESULT_OK)
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