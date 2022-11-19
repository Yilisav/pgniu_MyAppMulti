package vik.com.example.myappmulti.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import vik.com.example.myappmulti.activities.CJobInfo
import vik.com.example.myappmulti.databinding.ItemDealLayoutBinding
import vik.com.example.myappmulti.model.DealModel

/********************************************************************************************************
 * Класс адаптер для списка элементов. Содержит логику выбора элементов, логику вывода информации       *
 * об элементе в строку списка.                                                                         *
 *******************************************************************************************************/
class CDealAdapter
/********************************************************************************************************
 * Конструктор.                                                                                         *
 * @param items - список элементов данных, информацию по которым нужноо выводить на экран.              *
 * @param onItemClickListener - обработчик кликов на элементы списка.                                   *
 * @param onItemRemoveListener - обработчик кликов на кнопку "удалить" элементов списка.                *
 *******************************************************************************************************/
    (
        private val items                       : MutableList<DealModel>,
        private val onItemClickListener         : (Int, DealModel) -> Unit,
        private val onItemRemoveListener        : (Int, DealModel) -> Unit
    ) : RecyclerView.Adapter<CDealAdapter.CDealViewHolder>()
    {
    /****************************************************************************************************
     * Вспомогательный класс, оотвечающий за визуальное отображение одного элемента данных.             *
     ***************************************************************************************************/
    inner class CDealViewHolder
    /****************************************************************************************************
     * Конструктор.                                                                                     *
     * @param binding - объект, хранящий ссылки на элементы интерфейса с одним элемонтом списка,        *
     * у которых указан идентификатор. *
     * @param onItemClickListener - обработчик кликов на элемент списка.                                *
     * @param onItemRemoveListener - обработчик кликов на кнопку "удалить" элемента списка.             *
     ***************************************************************************************************/
        (
        private  val binding                  : ItemDealLayoutBinding,
        private val onItemClickListener       : (Int, DealModel) -> Unit,
        private val onItemRemoveListener      : (Int, DealModel) -> Unit
        )                                     : RecyclerView.ViewHolder(binding.root)
    {
       /**Элемент данных, который отображается в текущем элементе списка.*/
        private lateinit var item             : DealModel

        init{
            //Обработка клика на все поля элемента, кроме кнопки с корзиной.
            binding.relativeLayoutItemDeal.setOnClickListener {
                onItemClickListener(items.indexOf(item), item)
            }
            //Обработка клика на кнопку с корзиной
            binding.deleteDealBut.setOnClickListener {
                onItemRemoveListener(items.indexOf(item), item)
            }
        }

        /************************************************************************************************
         * Метод описывает логику вывода элемента данных в строку списка.                               *
         * @param newItem - элемент данных для вывода.                                                  *
         ***********************************************************************************************/
        fun bind(
            newItem                             : DealModel
        )
        {
            item                                = newItem
            binding.tvServiceHolder.text        = newItem.service
            binding.tvStatusServiceHolder.text  = newItem.serviceStatus
            binding.tvFirstNameHolder.text      = newItem.clientFirstName
            binding.tvLastNameHolder.text       = newItem.clientLastName
            binding.tvIdServiceHolder.text      = newItem.idService.toString()
        }
    }

    /****************************************************************************************************
     * Метод вызывается в момент создания новой строки в списке.                                        *
     * Указывает, какой файл с разметкой внешнего вида использовать.                                    *
     * @param parent - ссылка на родительский элемент - RecyclerView.                                   *
     ***************************************************************************************************/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CDealViewHolder {
       val  binding                             = ItemDealLayoutBinding.inflate(
           LayoutInflater.from(parent.context),parent,false)
       return CDealViewHolder(
           binding,
           onItemClickListener,
           onItemRemoveListener
       )
    }
    /****************************************************************************************************
     * Метод вызывается в момент назначения элемента данных с порядковым номером position на вывод в    *
     * строке списка holder.                                                                            *
     * @param holder - строка списка с управляющими графическими элементами.                            *
     * @param position - порядковый номер элемента данных в списке.                                     *
     ***************************************************************************************************/
    override fun onBindViewHolder(holder: CDealViewHolder, position: Int) {
        holder.bind(items[position])
    }
    /****************************************************************************************************
     * Возвращает актуальное количество элементов в списке.                                             *
     * @return общее количество элементов данных в списке.                                              *
     ***************************************************************************************************/
    override fun getItemCount(): Int {
        return items.size

    }
//    fun setList(list: ArrayList<DealModel>){
//        dealList = list
//        notifyDataSetChanged()
//    }

//    interface IItemClickListener{
//        fun onItemClick(position: Int, userList: DealModel)
//        fun onItemDeleteClick(position: Int, userList: DealModel)
//    }
}