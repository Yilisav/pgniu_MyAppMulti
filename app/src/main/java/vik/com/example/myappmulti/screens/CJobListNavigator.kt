package vik.com.example.myappmulti.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import vik.com.example.myappmulti.CApplication
import vik.com.example.myappmulti.activities.CJobInfo
import vik.com.example.myappmulti.activities.CJobsMain
import vik.com.example.myappmulti.adapter.CDealAdapter
import vik.com.example.myappmulti.databinding.FragmentJobListNavLayoutBinding
import vik.com.example.myappmulti.model.DealModel
import vik.com.example.myappmulti.viewmodels.CViewModelActivityList
import vik.com.example.myappmulti.viewmodels.CViewModelFactory
import java.util.*


class CJobListNavigator : Fragment()
    {
    /** @param binding - Объект класса, содержащий ссылки на управляющие графические элементы интерфейса пользователя.  */
    private lateinit var binding             : FragmentJobListNavLayoutBinding
    private lateinit var resultLauncherEdit  : ActivityResultLauncher<Intent>
    private lateinit var resultLauncherAdd   : ActivityResultLauncher<Intent>
    private lateinit var recyclerView        : RecyclerView

    private val viewModelActivityList: CViewModelActivityList by viewModels {
        CViewModelFactory((activity?.application as CApplication).repositoryDeals)
    }

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
//        items.add(DealModel(UUID.randomUUID(),123,"новая", "Petrov", "Vasya", "Принтер", "Ремонт", "не включается", "2022-09-11","","","","ул. Луначарского, 83 A, Пермь, Пермский край, 614000",58.00688436271366, 56.23924621133853))
//        items.add(DealModel(UUID.randomUUID(),135,"новая", "Arhipov", "Artem", "Компьютер", "Ремонт", "не включается", "2022-01-22","","","","Петропавловская ул., 86, Пермь, Пермский край, 614068",58.005641432435944, 56.19750034280017))
//        items.add(DealModel(UUID.randomUUID(),295,"новая", "Sidirov", "Jenya", "Монитор", "Ремонт", "не включается", "2022-11-11","","","","ул. Докучаева, 34 82, Пермь, Пермский край, 614031",58.03678793961305, 56.1373172040946))
//        items.add(DealModel(UUID.randomUUID(),335,"в работе", "Ivanov", "Ivan", "Принтер", "Ремонт", "не включается", "2022-10-02","","Komkov","Alex","ул. Васнецова, 7/1, Пермь, Пермский край, 614112",58.11570953264437, 56.2884596326834))
//        items.add(DealModel(UUID.randomUUID(),113,"новая", "Afonin", "Vasya", "Монитор", "Ремонт", "не включается", "2022-04-05","","","","ул. Центральная ферма, 4, Пермь, Пермский край, 614025",57.96355683693899, 56.28434898015005))
//        items.add(DealModel(UUID.randomUUID(),266,"новая", "Suvorov", "Petr", "Принтер", "Настройка", "не печатает", "2022-09-30","","","","ул. Целинная, 55-200, Пермь, Пермский край, 614056",58.050839705237024, 56.34866887228363))
//        items.add(DealModel(UUID.randomUUID(),359,"закрыта", "Suvorov", "Vova", "Компьютер", "Установка ОС", "не загружается", "2022-05-10","2022-06-11","Ivanov","Petr","ул. Тихорецкая, 16-2, Пермь, Пермский край, 614026",58.12017670756103, 56.40421497215268))
//        items.add(DealModel(UUID.randomUUID(),135,"в работе", "Ivanov", "Vasya", "Принтер", "Картридж", "кончилась краска", "2022-10-02","","Komkov","Alex","ул. Восстания, 160, Пермь, Пермский край, 614014",58.02552791001873, 56.33308530089634))
//        items.add(DealModel(UUID.randomUUID(),291,"в работе", "Lenin", "Yan", "Компьютер", "Вирусы", "заблокирован", "2022-10-02","","Ivanov","Alex","ул. Гусарова, 16, Пермь, Пермский край, 614021",57.96380524303699, 56.21839123431603))
//        items.add(DealModel(UUID.randomUUID(),385,"в работе", "Newton", "Isak", "Монитор", "Настройка", "изображение розовое", "2022-10-02","","Ivanov","Alex","Бенгальская ул., 2, Пермь, Пермский край, 614037",58.14539578363232, 56.44367999165525))
//        items.add(DealModel(UUID.randomUUID(),523,"закрыта", "Petrov", "Vasya", "Принтер", "Ремонт", "не печтатает", "2022-11-13","2022-11-16","Komkov","Alex","ш. Космонавтов, 174, Пермь, Пермский край, 614065",57.96611280035113, 56.14375904170536))
//        items.add(DealModel(UUID.randomUUID(),216,"новая", "Isaev", "Kirill", "Компьютер", "Настройка", "нет второго диска", "2022-09-30","","","","ул. Саранская, 3, Пермь, Пермский край, 614042",57.99923991543015, 55.92473535947532))
//        items.add(DealModel(UUID.randomUUID(),381,"в работе", "Mazur", "Vova", "Принтер", "Ремонт", "мажет изображение, скрипит", "2022-10-02","","Ivanov","Alex","ул. Маршала Толбухина, 23, Пермь, Пермский край, 614030",58.098453220915815, 56.29905438547104))
//        items.add(DealModel(UUID.randomUUID(),261,"новая", "Viktuk", "Sasha", "Принтер", "Настройка", "не печатает", "2022-09-30","","","","ул. Промышленная, 13, Краснокамск, Пермский край, 617062",58.08174081688452, 55.78780534862954))
//        items.add(DealModel(UUID.randomUUID(),444,"закрыта", "Yanin", "Vova", "Принтер", "Ремонт", "не берет бумагу", "2022-11-11","2022-11-11","Ivanov","Petr","ул. Уральская, 116, Пермь, Пермский край, 614060",58.01939073443074, 56.273099394474755))

        // Create a new coroutine in the lifecycleScope
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // This happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModelActivityList.allDeals.collect { newItems ->
                    // Process item
                    items.clear()
                    items.addAll(newItems)
                    binding.recyclerViewDeal.adapter?.notifyDataSetChanged()
                }
            }
        }


        /** работа с RecyclerView на макете(виде) frag_job_list_navigator*/
        recyclerView                   = binding.recyclerViewDeal
        recyclerView.adapter           = CDealAdapter(
            // Список элементов
            items,
           // Обработчик клика по элементу.
            { _, item ->
                //Вызов активности с информацией по объекту, передача туда параметров.
                val intent = Intent(requireContext(), CJobInfo::class.java)
                    intent.putExtra("KEY_DEAL_ID", item.id.toString())
                    //intent.putExtra("KEY_INDEX", index)
                   // intent.putExtra("KEY_CLIENT_LAST_NAME", item.clientLastName)
                resultLauncherEdit.launch(intent)
            },
            //Обработчик клика на кнопку "удалить" элемента.
            { _, item ->
                viewModelActivityList.delete(item)
                //items.removeAt(index)
                //binding.recyclerViewDeal.adapter?.notifyItemRemoved(index)
            }
        )


        /** обработка объекта при редактированпии */
        resultLauncherEdit = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent?       = result.data
//                val index               = data?.getIntExtra("KEY_INDEX",-1)!!.toInt()
//                val idClientLastName    = data.getStringExtra("NEW_NAME") ?: ""
//                if (index < 0){ // если проблемы с полученными данными
//                    println(" No param from CJobInfo")
//                } else { // если всё нормально
//                    //обновляем объект в списке
//                    items[index].clientLastName = idClientLastName
//                    //сообщаем адаптеру об изменениях и необходимости обновления вывода на экран
//                    binding.recyclerViewDeal.adapter?.notifyItemChanged(index)
//
//                }
            }else{
                (requireContext() as CJobsMain).exitFromJobsMain()
            }
        }

        /** обработка клика по кнопке добавления fab*/
        binding.fab.setOnClickListener {
            val intent = Intent(activity, CJobInfo::class.java)
            //intent.putExtra("KEY_INDEX", 999)
            resultLauncherAdd.launch(intent)
        }


        /** обработка объекта при добавлении */
        resultLauncherAdd = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
//                val data: Intent?           = result.data
//                val indexNew                = data?.getIntExtra("KEY_INDEX",-1)!!.toInt()
//                val idClientLastNameNew     = data.getStringExtra("NEW_NAME")?:""
//                if (indexNew == 999){
//                //добавляем объект в список
//                items.add(DealModel(UUID.randomUUID(),0,"new", idClientLastNameNew, "Нулевой километр Пермского края", "empty", "empty", "", "","","","","ул. 25 Октября, 9, Пермь, Пермский край, 614045",58.014179483715004, 56.24900489119622))
//                //сообщаем адаптеру об изменениях и необходимости обновления вывода на экран
//                binding.recyclerViewDeal.adapter?.notifyItemInserted(items.size-1)
//                } else {// если проблемы с полученными данными
//                    println(" No param from CJobInfo")
//                }
            }else{
                (requireContext() as CJobsMain).exitFromJobsMain()
            }
        }
    }
} //main class
