package vik.com.example.myappmulti

import android.app.Application
import vik.com.example.myappmulti.repositories.CRepositoryDeals
import vik.com.example.myappmulti.room.CDatabase

class CApplication : Application()
{
    private val database by lazy { CDatabase.getDatabase(this) }
    val repositoryDeals by lazy { CRepositoryDeals(database.daoDeals()) }

//    // Активация яндекс карт по ключу
//    MapKitFactory.setApiKey("340e1f06-59b9-4e22-bd0f-a6c9903da439")
}