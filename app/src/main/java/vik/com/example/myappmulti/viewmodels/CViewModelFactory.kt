package vik.com.example.myappmulti.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vik.com.example.myappmulti.repositories.CRepositoryDeals

class CViewModelFactory (
    private val repositoryDeals: CRepositoryDeals
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CViewModelActivityList::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CViewModelActivityList(repositoryDeals) as T
            }
            //второй класс VieModels и т.д.
            if (modelClass.isAssignableFrom(CViewModelDealInfo::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CViewModelDealInfo(repositoryDeals) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
