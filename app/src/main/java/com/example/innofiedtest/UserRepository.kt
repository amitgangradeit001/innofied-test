package com.example.innofiedtest

class UserRepository constructor(
    private val retrofitService: RetrofitService
) {
    fun getUsers(pageNumber: Int,pageSize: Int) = retrofitService.getUsers(pageNumber, pageSize)


}