package vik.com.example.myappmulti.activities


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.model.ObjPersone
import vik.com.example.myappmulti.databinding.CmainLayoutBinding


class CMainActivity : AppCompatActivity() {

    // переменная для работы с элементами на макетах mainActivity
    private lateinit var binding                         : CmainLayoutBinding
    private lateinit var resultLauncher                  : ActivityResultLauncher<Intent>
    private lateinit var resultLauncherStoragePermission : ActivityResultLauncher<Array<String>>
    private var user                                     = ObjPersone("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CmainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // обработка ответа на запрос доступа к файловой системе
        resultLauncherStoragePermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        {map: Map<String,Boolean> ->
            if (map[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true){ }else{ }
        }
        // запрос на доступ к памяти и камере
        checkRequestPermission()

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
            resultLauncher.launch(activityAbout)
        }
        // обработка ответа активности JobsMain
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
            }
        }


    } // onCreate}

    private fun checkRequestPermission() {
        val allPermissions = listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
//        val askPermission = mutableListOf<String>()
//        for (i in 0..allPermissions.size){
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                   allPermissions[i]
//                ) == PackageManager.PERMISSION_DENIED)
//                askPermission.add(allPermissions[i])
//        }
        val askPermission = allPermissions.filter {
            return@filter ContextCompat.checkSelfPermission(this, it
            ) == PackageManager.PERMISSION_DENIED }
        if (askPermission.isNotEmpty()){
        resultLauncherStoragePermission.launch(askPermission.toTypedArray())}
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
//                val info = Intent(this, CJobInfo::class.java)
//                resultLauncher.launch(info)
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
