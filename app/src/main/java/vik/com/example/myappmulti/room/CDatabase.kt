package vik.com.example.myappmulti.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vik.com.example.myappmulti.dao.IDAODeal
import vik.com.example.myappmulti.model.DealModel
import vik.com.example.myappmulti.model.ObjPersone

@Database(
    entities = arrayOf(
        DealModel::class,
    //    ObjPersone::class
    ), version = 1, exportSchema = false)
public abstract class CDatabase  : RoomDatabase() {

    abstract fun daoDeals(): IDAODeal

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CDatabase? = null

        fun getDatabase(context: Context): CDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CDatabase::class.java,
                    "database.db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}