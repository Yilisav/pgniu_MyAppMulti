package vik.com.example.myappmulti.repositories

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import vik.com.example.myappmulti.dao.IDAODeal
import vik.com.example.myappmulti.model.DealModel
import java.util.UUID

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CRepositoryDeals (
    private val daoDeals: IDAODeal) {

        fun getAll()                            = daoDeals.getAll()
        // Room executes all queries on a separate thread.
        // Observed Flow will notify the observer when the data has changed.
        val allDeals: Flow<List<DealModel>> = daoDeals.getAll()

        suspend fun getById(
            id : UUID) = daoDeals.getById(id)


        // By default Room runs suspend queries off the main thread, therefore, we don't need to
        // implement anything else to ensure we're not doing long running database work
        // off the main thread.
        @Suppress("RedundantSuspendModifier")
        @WorkerThread
        suspend fun insert(deal: DealModel) {
            daoDeals.insertAll(deal)
        }
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(deal: DealModel) {
        daoDeals.delete(deal)
    }
    suspend fun update(deal: DealModel) {
        daoDeals.update(deal)
    }
    }
