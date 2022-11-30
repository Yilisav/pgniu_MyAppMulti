package vik.com.example.myappmulti.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import vik.com.example.myappmulti.model.DealModel
import vik.com.example.myappmulti.repositories.CRepositoryDeals

class CViewModelActivityList (
     private val repositoryDeals: CRepositoryDeals) : ViewModel() {

        // Using LiveData and caching what allWords returns has several benefits:
        // - We can put an observer on the data (instead of polling for changes) and only update the
        //   the UI when the data actually changes.
        // - Repository is completely separated from the UI through the ViewModel.
        val allDeals: Flow<List<DealModel>> = repositoryDeals.allDeals

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        fun insert(deal: DealModel) = viewModelScope.launch {
            repositoryDeals.insert(deal)
        }
        fun delete(deal: DealModel) = viewModelScope.launch {
            repositoryDeals.delete(deal)
        }
    }
