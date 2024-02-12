package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeapplicationtest.network.StudentsListRepository
import com.example.composeapplicationtest.viemodels.StudentViewModel


class MyViewModelFactory constructor(private val repository: StudentsListRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            StudentViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}