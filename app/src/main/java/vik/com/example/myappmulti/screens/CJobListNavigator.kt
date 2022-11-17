package vik.com.example.myappmulti.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vik.com.example.myappmulti.MAIN
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.activities.CJobInfo
import vik.com.example.myappmulti.adapter.DealAdapter
import vik.com.example.myappmulti.databinding.FragmentJobListNavLayoutBinding
import vik.com.example.myappmulti.model.DealModel


class CJobListNavigator : Fragment() //, View.OnClickListener
    {

    //Объект класса, содержащий ссылки на управляющие графические элементы интерфейса пользователя.
    private lateinit var binding            : FragmentJobListNavLayoutBinding
    private lateinit var resultLauncherInfo : ActivityResultLauncher<Intent>
    lateinit var adapter                    : DealAdapter
    lateinit var recyclerView               : RecyclerView


    //Вызывается для создания компонентов внутри фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Связываем код актиности с файлом, описывающим внешний вид активности.
        binding = FragmentJobListNavLayoutBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

//        resultLauncherInfo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent? = result.data
//            }
//        }
        initial()


//        val items = mutableListOf<DealModel>()
//        items.add(DealModel(123,"новая", "Petrov", "Vasya", "Принтер", "Ремонт", "не включается", "2022-09-11","","",""))
//        items.add(DealModel(135,"новая", "Arhipov", "Artem", "Компьютер", "Ремонт", "не включается", "2022-01-22","","",""))
//        items.add(DealModel(295,"новая", "Sidirov", "Jenya", "Монитор", "Ремонт", "не включается", "2022-11-11","","",""))
//        items.add(DealModel(335,"в работе", "Ivanov", "Ivan", "Принтер", "Ремонт", "не включается", "2022-10-02","","Komkov","Alex"))
//        items.add(DealModel(113,"новая", "Afonin", "Vasya", "Монитор", "Ремонт", "не включается", "2022-04-05","","",""))
//        items.add(DealModel(266,"новая", "Suvorov", "Petr", "Принтер", "Настройка", "не печатает", "2022-09-30","","",""))
//        items.add(DealModel(359,"закрыта", "Suvorov", "Vova", "Компьютер", "Установка ОС", "не загружается", "2022-05-10","2022-06-11","Ivanov","Petr"))
//        items.add(DealModel(135,"в работе", "Ivanov", "Vasya", "Принтер", "Картридж", "кончилась краска", "2022-10-02","","Komkov","Alex"))
//        items.add(DealModel(291,"в работе", "Lenin", "Yan", "Компьютер", "Вирусы", "заблокирован", "2022-10-02","","Ivanov","Alex"))
//        items.add(DealModel(385,"в работе", "Newton", "Isak", "Монитор", "Настройка", "изображение розовое", "2022-10-02","","Ivanov","Alex"))
//        items.add(DealModel(523,"закрыта", "Petrov", "Vasya", "Принтер", "Ремонт", "не печтатает", "2022-11-13","2022-11-16","Komkov","Alex"))
//        items.add(DealModel(216,"новая", "Isaev", "Kirill", "Компьютер", "Настройка", "нет второго диска", "2022-09-30","","",""))
//        items.add(DealModel(381,"в работе", "Mazur", "Vova", "Принтер", "Ремонт", "мажет изображение, скрипит", "2022-10-02","","Ivanov","Alex"))
//        items.add(DealModel(261,"новая", "Viktuk", "Sasha", "Принтер", "Настройка", "не печатает", "2022-09-30","","",""))
//        items.add(DealModel(444,"закрыта", "Yanin", "Vova", "Принтер", "Ремонт", "не берет бумагу", "2022-11-11","2022-11-11","Ivanov","Petr"))



//        binding.rvDeal.layoutManager     = LinearLayoutManager(this)
//        val items : MutableList<DealModel> = dealList()
//        recyclerView = binding.rvDeal
//        recyclerView.adapter           = DealAdapter(
            //Список элементов
//            items,
//            //Обработчик клика по элементу.
//            { index, item ->
//                //Вызов активности с информацией по объекту, передача туда параметров.
//                val intent                  = Intent(MAIN, CJobInfo::class.java)
//                intent.putExtra("KEY_INDEX", index)
//                intent.putExtra("KEY_ID_SERVICE", item.idService)
//                resultLauncherInfo.launch(intent)
//            },
//            //Обработчик клика на кнопку "удалить" элемента.
//            { index, _ ->
//                items.removeAt(index)
//                binding.rvDeal.adapter?.notifyItemRemoved(index)
//            }
//        )
    }

    /************************************************************************************************
     * Обработка события завершения активности с информацией по объекту в режиме редактирования     *
     * существующего объекта.                                                                       *
     ***********************************************************************************************/
//    resultLauncherInfo            = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            //Получение параметров из дочерней активности
//            val data                    : Intent?
//                    = result.data
//            val name                    = data?.getStringExtra("KEY_OBJECT_NAME") ?: ""
//            val index                   = data?.getIntExtra("KEY_INDEX", -1)?: -1
//            //Если какие-то проблемы с данными, выводи сообщение или как-то обрабатываем.
//            if (index<0)
//            {
//                //TODO Сообщение о проблеме в передаче данных
//            }
//            else
//            {
//                //Если всё нормально,
//                //актуализируем объект в списке данных.
//                items[index].name       = name
//                //Говорим адаптеру списка, что конкретная единица данных обновлена,
//                //нужно повторно её вывести на экран.
//                (binding.rvObjects.adapter as CRecyclerViewAdapterObjects).notifyItemChanged(index)
//            }
//        }
//    }

    fun dealList()                       : ArrayList<DealModel> {
    //Тестовый список объектов, которые будут выводится пользователю.
        val items                       = mutableListOf<DealModel>()
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
        return items as ArrayList<DealModel>
    }
    private fun initial() {
        recyclerView = binding.rvDeal
        adapter = DealAdapter()
        recyclerView.adapter = adapter
        adapter.setList(dealList())

    }
//    fun allDeal (): ArrayList<DealModel> {
//        val userList = ArrayList<DealModel>()
//        val  user = DealModel (133,"Petrov", "Vasya")
//        userList.add(user)
//        val  user2 = DealModel (321,"Ivanov", "Ilya")
//        userList.add(user2)
//        val  user3 = DealModel (654,"Sidirov", "Ivan")
//        userList.add(user3)
//        val  user4 = DealModel (987,"Arhipov", "Petr")
//        userList.add(user4)
//        val  user5 = DealModel (789,"Afonin", "Kolya")
//        userList.add(user5)
//        val  user6 = DealModel (456,"Petrov", "Vasya")
//        userList.add(user6)
//        val  user7 = DealModel (984,"Ivanov", "Ilya")
//        userList.add(user7)
//        val  user8 = DealModel (962,"Sidirov", "Ivan")
//        userList.add(user8)
//        val  user9 = DealModel (364,"Arhipov", "Petr")
//        userList.add(user9)
//        val  user10 = DealModel (256,"Afonin", "Kolya")
//        userList.add(user10)
//        val  user11 = DealModel (136,"Petrov", "Vasya")
//        userList.add(user11)
//        val  user12 = DealModel (234,"Ivanov", "Ilya")
//        userList.add(user12)
//        val  user13 = DealModel (192,"Sidirov", "Ivan")
//        userList.add(user13)
//        val  user14 = DealModel (852,"Arhipov", "Petr")
//        userList.add(user14)
//        val  user15 = DealModel (174,"Afonin", "Kolya")
//        userList.add(user15)
//        return userList
//    }

}