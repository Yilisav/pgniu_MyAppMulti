package vik.com.example.myappmulti.activities


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.CmainBinding
import vik.com.example.myappmulti.Objects.CPersone


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
            val activityJobsList = Intent(this, CJobsMain::class.java)
            user.login = binding.loginInText.editText?.text.toString()
            user.password = binding.passwordInText.editText?.text.toString()
//            activityJobsList.putExtra("MY_LOGIN", user.login)
            binding.loginInText.editText?.setText("")
            binding.passwordInText.editText?.setText("")
            resultLauncher.launch(activityJobsList)
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
    // Сохраняем состояние полей ввода
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("LOGIN",binding.loginInText.editText?.text.toString())
        outState.putString("PASSWORD",binding.passwordInText.editText?.text.toString())
    }
    // Восстанавливаем состояние полей ввода
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val login = savedInstanceState.getString("LOGIN", "")
        val password = savedInstanceState.getString("PASSWORD", "")
        binding.loginInText.editText?.setText(login)
        binding.passwordInText.editText?.setText(password)
    }
}
