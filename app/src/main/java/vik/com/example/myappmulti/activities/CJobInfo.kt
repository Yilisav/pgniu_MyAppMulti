package vik.com.example.myappmulti.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.JobinfoBinding



class CJobInfo : AppCompatActivity() {

    private lateinit var binding: JobinfoBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = JobinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                finish();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}