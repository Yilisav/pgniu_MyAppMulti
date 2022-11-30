package vik.com.example.myappmulti.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "dealTables")
class DealModel (
    @PrimaryKey
    var id : UUID = UUID.randomUUID(),
    @ColumnInfo(name = "idService") //- название поля  в таблице БД
    var idService: Int = 0,
    @ColumnInfo(name = "serviceStatus")         var serviceStatus       : String = "",
    @ColumnInfo(name = "clientLastName")        var clientLastName      : String = "",
    @ColumnInfo(name = "clientFirstName")       var clientFirstName     : String = "",
    @ColumnInfo(name = "service")               var service             : String = "",
    @ColumnInfo(name = "serviceCategory")       var serviceCategory     : String = "",
    @ColumnInfo(name = "serviceDescription")    var serviceDescription  : String = "",
    @ColumnInfo(name = "dateInCome")            var dateInCome          : String = "",
    @ColumnInfo(name = "dateExecution")         var dateExecution       : String = "",
    @ColumnInfo(name = "employeeLastName")      var employeeLastName    : String = "",
    @ColumnInfo(name = "employeeFirstName")     var employeeFirstName   : String = "",
    @ColumnInfo(name = "address")               var address             : String = "",
    @ColumnInfo(name = "latitude")              var latitude            : Double = 0.0,
    @ColumnInfo(name = "longitude")             var longitude           : Double = 0.0,
)

//class DealModel (
//    val idService: Int,
//    val clientLastName: String,
//    val clientFirstName: String,
//)