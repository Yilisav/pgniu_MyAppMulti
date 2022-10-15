package vik.com.example.myappmulti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Calculator : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val buttonClose : Button = findViewById(R.id.close)

        buttonClose.setOnClickListener{
            finish()
        }
    }
}