package com.example.innofiedtest.models

data class UsersDataClass(
    val page: Int,
    val per_page:Int,
    val total: Int,
    val total_pages: Int,
    val data: ArrayList<Data>
)
