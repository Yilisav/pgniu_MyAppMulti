package vik.com.example.myappmulti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast


class CMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cmain)

        val buttonCalculator: Button = findViewById(R.id.calc)
        val buttonMap: Button = findViewById(R.id.map)
        val buttonAbout: Button = findViewById(R.id.aboutButton)

        // обработка кнопок на активности
        buttonMap.setOnClickListener {
            Toast.makeText(this, "push button map", Toast.LENGTH_SHORT).show()
            val activityMap = Intent(this@CMainActivity, CMap::class.java)
            startActivity(activityMap)
        }
        buttonAbout.setOnClickListener {
            val activityAbout = Intent(this@CMainActivity, CAbout::class.java)
            startActivity(activityAbout)
        }

        buttonCalculator.setOnClickListener {
            val activityCalculator = Intent(this@CMainActivity, CCalculator::class.java)
            startActivity(activityCalculator)
        }

    }
    // меню
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }
    // обаботка кнопок меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.mMap -> {
                val activityMap = Intent(this@CMainActivity, CMap::class.java)
                startActivity(activityMap)
                true
            }
            R.id.mCalculator -> {
                val activityCalculator = Intent(this@CMainActivity, CCalculator::class.java)
                startActivity(activityCalculator)
                true
            }
            R.id.mAbout -> {

                val activityAbout = Intent(this@CMainActivity, CAbout::class.java)
                startActivity(activityAbout)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
