package com.example.innofiedtest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innofiedtest.UserRepository
import com.example.innofiedtest.models.Data
import com.example.innofiedtest.models.UsersDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainUserViewModel constructor(private val userRepository: UserRepository) : ViewModel() {


    var pageNumber = 1
    private val pageSize = 5
    val usersData = MutableLiveData<ArrayList<Data>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    val isNetworkAvailable = MutableLiveData<Boolean>()

    fun getUsers() {
        val response = userRepository.getUsers(pageNumber,pageSize)
        response.enqueue(object : Callback<UsersDataClass> {
            override fun onResponse(
                call: Call<UsersDataClass>,
                response: Response<UsersDataClass>
            ) {

                usersData.value = response.body()?.data

                isLoading.value = false
                pageNumber +=1

            }

            override fun onFailure(call: Call<UsersDataClass>, t: Throwable) {
                errorMessage.value = t.message
                isLoading.value = false
            }
        })
    }



}