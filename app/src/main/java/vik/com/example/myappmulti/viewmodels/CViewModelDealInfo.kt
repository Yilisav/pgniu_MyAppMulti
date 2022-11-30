package vik.com.example.myappmulti.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vik.com.example.myappmulti.model.DealModel
import vik.com.example.myappmulti.repositories.CRepositoryDeals
import java.util.*

class CViewModelDealInfo(
    private val repositoryDeals: CRepositoryDeals
) : ViewModel()

{
    var idIndex : Int = -1

    private var id : UUID? = null

    var item : DealModel? = null

    fun setIndex(
        index : Int
    ){
        this.idIndex
    }
    suspend fun setId(
        id : UUID?
    )
    {
        this.id = id
        id ?.let{
            item = repositoryDeals.getById(id)
        }
        ?:
        run {
            item = null}

    }

    fun save (
        idService: Int,
        serviceStatus: String,
        clientLastName: String,
        clientFirstName: String,
        service: String,
        serviceCategory: String,
        serviceDescription: String,
        dateInCome: String,
        dateExecution: String,
        employeeLastName: String,
        employeeFirstName: String,
        address : String,
        latitude: Double,
        longitude: Double,
    )
    {

        viewModelScope.launch(Dispatchers.IO)
        {
            item?.let{
                item = item ?: DealModel()
                item!!.idService = idService
                item!!.serviceStatus = serviceStatus
                item!!.clientLastName = clientLastName
                item!!.clientFirstName = clientFirstName
                item!!.service = service
                item!!.serviceCategory = serviceCategory
                item!!.serviceDescription = serviceDescription
                item!!.dateInCome = dateInCome
                item!!.dateExecution = dateExecution
                item!!.employeeLastName = employeeLastName
                item!!.employeeFirstName = employeeFirstName
                item!!.address = address
                item!!.latitude = latitude
                item!!.longitude = longitude

                repositoryDeals.update(item!!)


            }?:run{

                //val newItem = DealModel(0)
                item = item ?: DealModel()
                item!!.idService = idService
                item!!.serviceStatus = serviceStatus
                item!!.clientLastName = clientLastName
                item!!.clientFirstName = clientFirstName
                item!!.service = service
                item!!.serviceCategory = serviceCategory
                item!!.serviceDescription = serviceDescription
                item!!.dateInCome = dateInCome
                item!!.dateExecution = dateExecution
                item!!.employeeLastName = employeeLastName
                item!!.employeeFirstName = employeeFirstName
                item!!.address = address
                item!!.latitude = latitude
                item!!.longitude = longitude

                repositoryDeals.insert(item!!)
                //item = repositoryDeals.getById(newItem.id)

            }
        }
    }
}