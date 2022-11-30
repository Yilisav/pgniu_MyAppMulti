package vik.com.example.myappmulti.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import vik.com.example.myappmulti.model.DealModel
import java.util.*

@Dao
interface IDAODeal {
    @Query("SELECT * FROM dealTables")
    fun getAll(): Flow<List<DealModel>>

    @Query("SELECT * FROM dealTables WHERE id =:id")
    suspend fun getById(
        id : UUID
    ): DealModel


//    @Query("SELECT * FROM dealTables WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Insert
    suspend fun insertAll(vararg deals: DealModel)

    @Update
    suspend fun update(vararg deals: DealModel)

    @Delete
    suspend fun delete(deal: DealModel)
}