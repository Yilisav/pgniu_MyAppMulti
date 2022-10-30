package vik.com.example.myappmulti.activities


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.person.CPersone


class CMainActivity : AppCompatActivity() {

//    private val activityMap = Intent(this, CMap::class.java)
//    private val activityAbout = Intent(this, CAbout::class.java)
//    private val activityCalculator = Intent(this, CCalculator::class.java)

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var login: TextInputLayout
    private lateinit var password: TextInputLayout
    private var loginIn = ""
    private var passwordIn = ""
     var user = CPersone("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cmain)

        login = findViewById(R.id.loginInText)
        password = findViewById(R.id.passwordInText)

        user.login = login.editText?.text.toString()
        user.password = password.editText?.text.toString()


        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
            }
        }

//        val buttonCalculator: Button = findViewById(R.id.calc)
//        val buttonMap: Button = findViewById(R.id.map)
        val buttonEnter: Button = findViewById(R.id.enterButton)
        val buttonAbout: Button = findViewById(R.id.aboutButton)

        // обработка кнопок на активности
//        buttonMap.setOnClickListener {
//             val activityMap = Intent(this@CMainActivity, CMap::class.java)
//            startActivity(activityMap)
//        }
//        buttonCalculator.setOnClickListener {
//             val activityCalculator = Intent(this@CMainActivity, CCalculator::class.java)
//            startActivity(activityCalculator)
//        }
        buttonEnter.setOnClickListener {
             val activityMap = Intent(this@CMainActivity, CMap::class.java)
            resultLauncher.launch(activityMap)
        }
        buttonAbout.setOnClickListener {
            val activityAbout = Intent(this@CMainActivity, CAbout::class.java)
//            startActivity(activityAbout)
            resultLauncher.launch(activityAbout)
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
//            R.id.mMap -> {
//                val activityMap = Intent(this@CMainActivity, CMap::class.java)
//                //startActivity(activityMap)
//                resultLauncher.launch(activityMap)
//                true
//            }
            R.id.mCalculator -> {
                val activityCalculator = Intent(this@CMainActivity, CCalculator::class.java)
                startActivity(activityCalculator)
//                resultLauncher.launch(activityCalculator)
                true

            }
            R.id.mAbout -> {
                val activityAbout = Intent(this@CMainActivity, CAbout::class.java)
                startActivity(activityAbout)
//                resultLauncher.launch(activityAbout)
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
//    // Сохраняем состояние экранов
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("INPUT",loginIn)
//        outState.putString("OUTPUT",passwordIn)
//    }
//    // Восстанавливаем состояние экранов
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        loginIn = savedInstanceState.getString("INPUT", "")
//        passwordIn = savedInstanceState.getString("OUTPUT", "")
//        val loginTextInput: TextInputLayout = findViewById(R.id.loginInText)
//        val passwordTextView: TextInputLayout = findViewById(R.id.passwordInText)
//        loginTextInput.editText?.text = loginIn
//        passwordTextView.text = passwordIn
//    }
}
