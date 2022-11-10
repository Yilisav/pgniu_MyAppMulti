package vik.com.example.myappmulti.activities


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputLayout
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.CalculatorBinding
import vik.com.example.myappmulti.databinding.CmainBinding
import vik.com.example.myappmulti.person.CPersone


class CMainActivity : AppCompatActivity() {

    // переменная для работы с элементами на макетах mainActivity
    private lateinit var binding: CmainBinding

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var user = CPersone("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CmainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
            }
        }

        // обработка кнопок на активности
        binding.enterButton.setOnClickListener {
            val activityMap = Intent(this, CMap::class.java)
            user.login = binding.loginInText.editText?.text.toString()
            user.password = binding.passwordInText.editText?.text.toString()
            activityMap.putExtra("MY_LOGIN", user.login)
            resultLauncher.launch(activityMap)
        }
        binding.aboutButton.setOnClickListener {
            val activityAbout = Intent(this, CAbout::class.java)
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
