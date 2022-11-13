package vik.com.example.myappmulti.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.MapBinding

class CMap : AppCompatActivity() {
    private lateinit var binding: MapBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
              item ->
            when(item.itemId) {
                R.id.page_list -> {
//                    val activityJobsList = Intent(this, CJobsList::class.java)
//                    resultLauncher.launch(activityJobsList)
                    setResult(RESULT_CANCELED)
                    finish()
                    true
                }
                R.id.page_map -> {

                    true
                }
                else -> false
            }
        }
//    // меню
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu_map_activity, menu)
//        return true
//    }
//    // обработка кнопок меню
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        var login : String = ""
//
//        if (intent.extras==null)
//        {
//            Toast.makeText(this, "login empty !", Toast.LENGTH_SHORT).show()
//        } else {
//            login = intent.extras!!.getString("MY_LOGIN").toString()
//        }
//        // Handle item selection
//        return when (item.itemId) {
//
//            R.id.mName -> {
//                Toast.makeText(this, login, Toast.LENGTH_SHORT).show()
//                true
//            }
//            R.id.mCalculator -> {
//                val activityCalculator = Intent(this, CCalculator::class.java)
//                resultLauncher.launch(activityCalculator)
//                true
//            }
//            R.id.mAbout -> {
//                val activityAbout = Intent(this, CAbout::class.java)
//                resultLauncher.launch(activityAbout)
//                true
//            }
//            R.id.mExit -> {
//                setResult(RESULT_OK)
//                finish()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
    }
}