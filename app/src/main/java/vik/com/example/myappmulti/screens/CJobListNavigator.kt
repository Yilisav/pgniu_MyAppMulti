package vik.com.example.myappmulti.screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.activities.CAbout
import vik.com.example.myappmulti.activities.CCalculator
import vik.com.example.myappmulti.activities.CJobInfo
import vik.com.example.myappmulti.activities.CJobsMain
import vik.com.example.myappmulti.adapter.CDealAdapter
import vik.com.example.myappmulti.databinding.FragmentJobListNavLayoutBinding
import vik.com.example.myappmulti.databinding.JobmainLayoutBinding
import vik.com.example.myappmulti.model.DealModel


class CJobListNavigator : Fragment()
    {
    /** @param binding - Объект класса, содержащий ссылки на управляющие графические элементы интерфейса пользователя.  */
    private lateinit var binding             : FragmentJobListNavLayoutBinding
    private lateinit var resultLauncherEdit  : ActivityResultLauncher<Intent>
    private lateinit var resultLauncherAdd   : ActivityResultLauncher<Intent>
    private lateinit var resultLauncherStoragePermission : ActivityResultLauncher<String>
    private lateinit var recyclerView        : RecyclerView


    // Функция вызывается для создания компонентов внутри фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        //Связываем код активности с файлом, описывающим макет(вид) активности.
        binding = FragmentJobListNavLayoutBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        /**Тестовый список объектов, которые будут выводится пользователю.*/
        var items = mutableListOf<DealModel>()
        items.add(DealModel(123,"новая", "Petrov", "Vasya", "Принтер", "Ремонт", "не включается", "2022-09-11","","",""))
        items.add(DealModel(135,"новая", "Arhipov", "Artem", "Компьютер", "Ремонт", "не включается", "2022-01-22","","",""))
        items.add(DealModel(295,"новая", "Sidirov", "Jenya", "Монитор", "Ремонт", "не включается", "2022-11-11","","",""))
        items.add(DealModel(335,"в работе", "Ivanov", "Ivan", "Принтер", "Ремонт", "не включается", "2022-10-02","","Komkov","Alex"))
        items.add(DealModel(113,"новая", "Afonin", "Vasya", "Монитор", "Ремонт", "не включается", "2022-04-05","","",""))
        items.add(DealModel(266,"новая", "Suvorov", "Petr", "Принтер", "Настройка", "не печатает", "2022-09-30","","",""))
        items.add(DealModel(359,"закрыта", "Suvorov", "Vova", "Компьютер", "Установка ОС", "не загружается", "2022-05-10","2022-06-11","Ivanov","Petr"))
        items.add(DealModel(135,"в работе", "Ivanov", "Vasya", "Принтер", "Картридж", "кончилась краска", "2022-10-02","","Komkov","Alex"))
        items.add(DealModel(291,"в работе", "Lenin", "Yan", "Компьютер", "Вирусы", "заблокирован", "2022-10-02","","Ivanov","Alex"))
        items.add(DealModel(385,"в работе", "Newton", "Isak", "Монитор", "Настройка", "изображение розовое", "2022-10-02","","Ivanov","Alex"))
        items.add(DealModel(523,"закрыта", "Petrov", "Vasya", "Принтер", "Ремонт", "не печтатает", "2022-11-13","2022-11-16","Komkov","Alex"))
        items.add(DealModel(216,"новая", "Isaev", "Kirill", "Компьютер", "Настройка", "нет второго диска", "2022-09-30","","",""))
        items.add(DealModel(381,"в работе", "Mazur", "Vova", "Принтер", "Ремонт", "мажет изображение, скрипит", "2022-10-02","","Ivanov","Alex"))
        items.add(DealModel(261,"новая", "Viktuk", "Sasha", "Принтер", "Настройка", "не печатает", "2022-09-30","","",""))
        items.add(DealModel(444,"закрыта", "Yanin", "Vova", "Принтер", "Ремонт", "не берет бумагу", "2022-11-11","2022-11-11","Ivanov","Petr"))

        /** работа с RecyclerView на макете(виде) frag_job_list_navigator*/
        recyclerView                   = binding.recyclerViewDeal
        recyclerView.adapter           = CDealAdapter(
            // Список элементов
            items,
           // Обработчик клика по элементу.
            { index, item ->
                //Вызов активности с информацией по объекту, передача туда параметров.
                val intent = Intent(this.requireContext(), CJobInfo::class.java)
                    intent.putExtra("KEY_INDEX", index)
                    intent.putExtra("KEY_CLIENT_LAST_NAME", item.clientLastName)
                resultLauncherEdit.launch(intent)
            },
            //Обработчик клика на кнопку "удалить" элемента.
            { index, _ ->
                items.removeAt(index)
                binding.recyclerViewDeal.adapter?.notifyItemRemoved(index)
            }
        )

        // обработка нажатия по кнопке "+" добавить
//        floatingButton = binding.

        /** обработка объекта при редактированпии */
        resultLauncherEdit = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent?       = result.data
                val index               = data?.getIntExtra("KEY_INDEX",-1)!!.toInt()
                val idClientLastName    = data.getStringExtra("NEW_NAME") ?: ""
                if (index < 0){ // если проблемы с полученными данными
                    println(" No param from CJobInfo")
                } else { // если всё нормально
                    //обновляем объект в списке
                    items[index].clientLastName = idClientLastName
                    //сообщаем адаптеру об изменениях и необходимости обновления вывода на экран
                    binding.recyclerViewDeal.adapter?.notifyItemChanged(index)

                }
            }
        }

        /** обработка клика по кнопке добавления fab*/
        binding.fab.setOnClickListener {
            val intent = Intent(this.requireContext(), CJobInfo::class.java)
            intent.putExtra("KEY_INDEX", 999)
            resultLauncherAdd.launch(intent)
        }
        /** обработка объекта при добавлении */
        resultLauncherAdd = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent?           = result.data
                val indexNew                = data?.getIntExtra("KEY_INDEX",-1)!!.toInt()
                val idClientLastNameNew     = data.getStringExtra("NEW_NAME")?:""
                if (indexNew == 999){
                //добавляем объект в список
                items.add(DealModel(999,"new", idClientLastNameNew, "empty", "empty", "empty", "", "","","",""))
                //сообщаем адаптеру об изменениях и необходимости обновления вывода на экран
                binding.recyclerViewDeal.adapter?.notifyItemInserted(items.size-1)
                } else {// если проблемы с полученными данными
                    println(" No param from CJobInfo")
                }
            }
        }



    } //onViewCreated
} //main class
