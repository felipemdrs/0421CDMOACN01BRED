package com.felipemdrs.roomaula63.home.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.felipemdrs.roomaula63.data.db.DatabaseUtils
import com.felipemdrs.roomaula63.data.db.entity.CivilizationEntity
import com.felipemdrs.roomaula63.home.model.CivilizationModel
import com.felipemdrs.roomaula63.home.repository.ICivilizationRepository
import com.felipemdrs.roomaula63.home.repository.api.CivilizationApiRepository
import com.felipemdrs.roomaula63.home.repository.db.CivilizationDbRepository
import kotlinx.coroutines.Dispatchers

class CivilizationViewModel(private val _context: Context): ViewModel() {
    private val civilizationApiRepository: ICivilizationRepository
    private val civilizationDbRepository: ICivilizationRepository

    init {
        civilizationApiRepository = CivilizationApiRepository()
        civilizationDbRepository = CivilizationDbRepository(DatabaseUtils.getDatabase(_context).civilizationDao())
    }

    fun obterLista() = liveData(Dispatchers.IO) {
        if (isOnline()) {
            val result = civilizationApiRepository.obterLista()

            (civilizationDbRepository as CivilizationDbRepository).addAllCivilizations(result.map {
                CivilizationEntity(it.id, it.name)
            })

            emit(result)
        } else {
            val result = civilizationDbRepository.obterLista()
            emit(result)
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            _context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }

        return false
    }

    class CivilizationViewModelFactory(private val context: Context): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CivilizationViewModel(context) as T
        }
    }
}