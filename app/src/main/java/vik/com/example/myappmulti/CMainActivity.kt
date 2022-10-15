package vik.com.example.myappmulti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



class CMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cmain)

        val buttonCalculator: Button = findViewById(R.id.calc)
        val buttonMap: Button = findViewById(R.id.map)
        val buttonAbout: Button = findViewById(R.id.aboutButton)

        buttonAbout.setOnClickListener {
            val about = Intent(this@CMainActivity, About::class.java)
            startActivity(about)
        }

        buttonCalculator.setOnClickListener {
            val calc = Intent(this@CMainActivity, Calculator::class.java)
            startActivity(calc)

        }

    }
}
