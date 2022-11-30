package vik.com.example.myappmulti.activities


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.system.Os.remove
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.yandex.mapkit.MapKitFactory
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.model.ObjPersone
import vik.com.example.myappmulti.databinding.CmainLayoutBinding
import java.io.File


class CMainActivity : AppCompatActivity() {

    // переменная для работы с элементами на макетах mainActivity
    private lateinit var binding                         : CmainLayoutBinding
    private lateinit var resultLauncher                  : ActivityResultLauncher<Intent>
    private lateinit var resultLauncherStoragePermission : ActivityResultLauncher<Array<String>>
    private lateinit var pref                            : SharedPreferences
    private var user                                     = ObjPersone("","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Активация яндекс карт по ключу
        MapKitFactory.setApiKey("340e1f06-59b9-4e22-bd0f-a6c9903da439")

        binding = CmainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // доступ к SharedPreferences
        pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        // обработка ответа на запрос доступа к файловой системе
        resultLauncherStoragePermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        {map: Map<String,Boolean> ->
            if (map[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true){ }else{ }
        }
        // обработка ответа от JobsMain
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {result ->
            if (result.resultCode == Activity.RESULT_OK) {
                finish()
            }else{
                with(pref.edit()){
                    remove(getString(R.string.Login))
                    apply()
                }
            }
        }
        // запрос на доступ к памяти, камере, местоположению
        checkRequestPermission()



        // чтение ранее заполненого имени пользователя
        val login = pref.getString(getString(R.string.Login), "")


        if (login != "")
        {
            val activityJobsList = Intent(this, CJobsMain::class.java)
            resultLauncher.launch(activityJobsList)
        }

        // обработка кнопок на активности
        binding.enterButton.setOnClickListener {
            onLoginClick()
//            val activityJobsList = Intent(this, CJobsMain::class.java)
//            val login_in = binding.loginInText.editText?.text.toString()
//            val password_in = binding.passwordInText.editText?.text.toString()
//
////            // чтение ранее заполненого имени пользователя
//            val login = pref.getString(getString(R.string.Login), "default value ")
//            val password = pref.getString("MY_PASSWORD", "default value")
//            binding.loginInText.editText?.setText("")
//            binding.passwordInText.editText?.setText("")
//            resultLauncher.launch(activityJobsList)
        }


        binding.aboutButton.setOnClickListener {
            val activityAbout = Intent(this, CAbout::class.java)
            startActivity(activityAbout)
        }
        // обработка ответа активности JobsMain



        // запись файла
//        val file = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "123.txt")

//        file.createNewFile()
//        Log.d("tetet",file.toString())
//        val text = listOf("test", "34534  ", "acore dfsd","98765151313")
//        file.printWriter().use { out ->
//            text.forEach{
//                out.println(it)
//            }
//        }
        //чтение файла
//        val text1 = file.readLines().toList()
//        Log.d("tetet",text1.joinToString ( "\n" ))

        // запись в преференсе
//        val pref= PreferenceManager.getDefaultSharedPreferences(applicationContext)
//        with(pref.edit()){
//            putInt("KEY_INTI", 123)
//            putString("KEY_STRINGI", "123")
//            apply()
//        }
//        Log.d("tetet", pref.toString())



    } // onCreate}

    private fun onLoginClick() {
        val activityJobsList = Intent(this, CJobsMain::class.java)
        val userName  =  checkLogin(binding.loginInText.editText?.text.toString()?:"",
            binding.passwordInText.editText?.text.toString()?:"")
        if (userName == "")
        {
            Toast.makeText(this, getString(R.string.WrongLoginOrPassword),Toast.LENGTH_LONG).show()
            return
        }
        with(pref.edit()){
            putString(getString(R.string.Login), userName)
            apply()
        }
        // очистка  полей ввода логина и пароля
        binding.loginInText.editText?.setText("")
        binding.passwordInText.editText?.setText("")
        resultLauncher.launch(activityJobsList)
    }

    // проверка введенного логина
    private fun checkLogin(
        login : String,
        password : String) :String
    {
        if (login == "test" && password=="222")
            return "good"
        return ""
    }

    /*** обработка запроса доступа к памяти,камере, местоположению*/
    private fun checkRequestPermission() {
        val allPermissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
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
                with(pref.edit()){
                    remove(getString(R.string.Login))
                    apply()
                }
                Toast.makeText(this, getString(R.string.InputNewUser), Toast.LENGTH_LONG).show()
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
