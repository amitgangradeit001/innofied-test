package com.example.innofiedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innofiedtest.adapter.UserAdapter
import com.example.innofiedtest.databinding.ActivityMainBinding
import com.example.innofiedtest.viewmodels.MainUserViewModel
import com.example.innofiedtest.viewmodels.MainUserViewModelFactory

class MainActivity : AppCompatActivity() {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var networkConnection: NetworkConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = binding.recyclerView
        networkConnection = NetworkConnection(this)

        val retrofitService = RetrofitService.getInstance()
        val userRepository = UserRepository(retrofitService)
        val mainUserViewModelFactory = MainUserViewModelFactory(userRepository)
        val viewModel =
            ViewModelProvider(this, mainUserViewModelFactory).get(MainUserViewModel::class.java)

        val userAdapter = UserAdapter()
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        viewModel.usersData.observe(this) {

            userAdapter.updateList(it)
            isLoading = false
        }

        viewModel.getUsers()

        binding.mainUserViewModel = viewModel

        viewModel.isLoading.observe(this) {
            if (!it) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        networkConnection.observe(this) {
            viewModel.isNetworkAvailable.value = it
        }

        viewModel.isNetworkAvailable.observe(this) {
            if (!it) {
                Toast.makeText(this, "Please check your internet connection!!", Toast.LENGTH_LONG)
                    .show()
            }
        }


        recyclerView.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                viewModel.getUsers()

            }

        })


    }
}