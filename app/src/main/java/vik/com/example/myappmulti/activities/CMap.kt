package vik.com.example.myappmulti.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vik.com.example.myappmulti.R

class CMap : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)
    }
    // меню
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_map_activity, menu)
        return true
    }
    // обработка кнопок меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var login : String = ""

        if (intent.extras==null)
        {
            Toast.makeText(this, "login empty !", Toast.LENGTH_SHORT).show()
        } else {
            login = intent.extras!!.getString("MY_LOGIN").toString()
        }
        // Handle item selection
        return when (item.itemId) {

            R.id.mName -> {
                Toast.makeText(this, login, Toast.LENGTH_SHORT).show()
//                resultLauncher.launch(activityCalculator)
                true
            }
            R.id.mCalculator -> {
                val activityCalculator = Intent(this, CCalculator::class.java)
                startActivity(activityCalculator)
//                resultLauncher.launch(activityCalculator)
                true
            }
            R.id.mAbout -> {
                val activityAbout = Intent(this, CAbout::class.java)
                startActivity(activityAbout)
//                resultLauncher.launch(activityAbout)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}