package com.linhtetko.uidesign.ui.vos

import com.linhtetko.uidesign.R

data class ShowVO(
    val id: Int,
    val image: Int,
    val time: String,
    val place: String,
){

    companion object{
        fun shows() = listOf(
            ShowVO(id = 1, image = R.drawable.placeholder_show1, time = "10:30 AM", place = "Placeholder Place"),
            ShowVO(id = 2, image = R.drawable.placeholder_show2, time = "5:30 PM", place = "Placeholder Place"),
            ShowVO(id = 3, image = R.drawable.placeholder_show3, time = "7:30 PM", place = "Placeholder Place"),
            ShowVO(id = 4, image = R.drawable.placeholder_show1, time = "10:30 PM", place = "Placeholder Place"),
        )
    }
}