package com.example.composeapplicationtest.viemodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeapplicationtest.network.StudentsListRepository
import com.example.composeappplication.models.DataClassModel
import com.example.composeappplication.models.DataClassModelItem
import kotlinx.coroutines.*

class StudentViewModel constructor(private val studentsListRepository: StudentsListRepository): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val countriesList = MutableLiveData<List<DataClassModelItem>?>()

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllCountriesList() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = studentsListRepository.getStudentsList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countriesList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}