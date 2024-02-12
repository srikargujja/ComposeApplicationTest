package com.example.composeapplicationtest.network


class StudentsListRepository constructor(private val retrofitAPIInterface: RetrofitAPIInterface) {

    suspend fun getStudentsList() = retrofitAPIInterface.getData()

}