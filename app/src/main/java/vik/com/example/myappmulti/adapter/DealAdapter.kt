package vik.com.example.myappmulti.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.databinding.FragmentJobListNavLayoutBinding
import vik.com.example.myappmulti.databinding.ItemDealLayoutBinding
import vik.com.example.myappmulti.model.DealModel

/********************************************************************************************************
 * Класс адаптер для списка элементов. Содержит логику выбора элементов, логику вывода информации       *
 * об элементе в строку списка.                                                                         *
 *******************************************************************************************************/
class DealAdapter
/********************************************************************************************************
 * Конструктор.                                                                                         *
 * @param items - список элементов данных, информацию по которым нужноо выводить на экран.              *
 * @param onItemClickListener - обработчик кликов на элементы списка.                                   *
 * @param onItemRemoveListener - обработчик кликов на кнопку "удалить" элементов списка.                *
 *******************************************************************************************************/
//    : RecyclerView.Adapter<DealAdapter.DealViewHolder>
    (
//        private val items                       : MutableList<DealModel>,
//        private val onItemClickListener         : (Int, CObject) -> Unit,
//        private val onItemRemoveListener        : (Int, CObject) -> Unit
    ) : RecyclerView.Adapter<DealAdapter.DealViewHolder>()
    {
    private var dealList                      = emptyList<DealModel>()
    /****************************************************************************************************
     * Вспомогательный класс, оотвечающий за визуальное отображение одного элемента данных.             *
     ***************************************************************************************************/
    inner class DealViewHolder
    /****************************************************************************************************
     * Конструктор.                                                                                     *
     * @param binding - объект, хранящий ссылки на элементы интерфейса, у которых указан идентификатор. *
     * @param onItemClickListener - обработчик кликов на элемент списка.                                *
     * @param onItemRemoveListener - обработчик кликов на кнопку "удалить" элемента списка.             *
     ***************************************************************************************************/
        (
//        private val binding                 : FragmentJobListNavLayoutBinding,
//        private val onItemClickListener     : (Int, DealModel) -> Unit,
//        private val onItemRemoveListener    : (Int, DealModel) -> Unit
        view: View
        )                                   : RecyclerView.ViewHolder(view)
//        )                                   : RecyclerView.ViewHolder(binding.root)
    {
//        Элемент данных, который отображается в текущем элементе списка.
//        private lateinit var item           : DealModel

//        init{
//            //Обработка клика на все поля элемента, кроме кнопки с корзиной.
//            binding.rvDeal.setOnClickListener {
//                onItemClickListener(items.indexOf(item), item)
//
//            }
//            //Обработка клика на кнопку с корзиной
//           val buttonDelete : ImageButton = binding.rvDeal.findViewById(R.id.delete_deal_but)
//
//            buttonDelete.setOnClickListener {
//                onItemRemoveListener(items.indexOf(item), item)
//
//            }
//        }

        /************************************************************************************************
         * Метод описывает логику вывода элемента данных в строку списка.                               *
         * @param newItem - элемент данных для вывода.                                                  *
         ***********************************************************************************************/
//        fun bind(
//            newItem                             : DealModel
//        )
//        {
//            item                                = newItem
//            val firstName : TextView = binding.rvDeal.findViewById(R.id.tv_first_name_holder)
//            val lastName : TextView = binding.rvDeal.findViewById(R.id.tv_last_name_holder)
//            val idService : TextView = binding.rvDeal.findViewById(R.id.tv_id_service_holder)
////            binding.tvFirstNameHolder.text  = newItem.clientFirstName
////            binding.tvLastNameHolder.text = newItem.clientLastName
////            binding.tvIdServiceHolder.text = newItem.idService.toString()
//            firstName.text = newItem.clientFirstName
//            lastName.text = newItem.clientLastName
//            idService.text  = newItem.idService.toString()
//        }
    }

    /****************************************************************************************************
     * Метод вызывается в момент создания новой строки в списке.                                        *
     * Указывает, какой файл с разметкой внешнего вида использовать.                                    *
     * @param parent - ссылка на родительский элемент - RecyclerView.                                   *
     ***************************************************************************************************/
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
//       val  binding                             = FragmentJobListNavLayoutBinding.inflate(
//           LayoutInflater.from(parent.context),parent,false)
//       return DealViewHolder(
//           binding,
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
       val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_deal_layout,parent,false)
       return DealViewHolder(

           view,
//           onItemClickListener,
//           onItemRemoveListener
       )
    }
    /****************************************************************************************************
     * Метод вызывается в момент назначения элемента данных с порядковым номером position на вывод в    *
     * строке списка holder.                                                                            *
     * @param holder - строка списка с управляющими графическими элементами.                            *
     * @param position - порядковый номер элемента данных в списке.                                     *
     ***************************************************************************************************/
    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
//        holder.bind(items[position])
        val firstName : TextView = holder.itemView.findViewById(R.id.tv_first_name_holder)
        val lastName : TextView = holder.itemView.findViewById(R.id.tv_last_name_holder)
        val idService : TextView = holder.itemView.findViewById(R.id.tv_id_service_holder)
        firstName.text = dealList[position].clientFirstName
        lastName.text = dealList[position].clientLastName
        idService.text = dealList[position].idService.toString()

    }
    /****************************************************************************************************
     * Возвращает актуальное количество элементов в списке.                                             *
     * @return общее количество элементов данных в списке.                                              *
     ***************************************************************************************************/
    override fun getItemCount(): Int {
//        return items.size
        return dealList.size
    }
    fun setList(list: ArrayList<DealModel>){
        dealList = list
        notifyDataSetChanged()
    }

//    interface IItemClickListener{
//        fun onItemClick(position: Int, userList: DealModel)
//        fun onItemDeleteClick(position: Int, userList: DealModel)
//    }
}