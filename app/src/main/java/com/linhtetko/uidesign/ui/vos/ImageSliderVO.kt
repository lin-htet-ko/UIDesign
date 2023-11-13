package com.linhtetko.uidesign.ui.vos

import com.linhtetko.uidesign.R

data class ImageSliderVO(
    val id: Int,
    val image: Int
){
    companion object{
        val images = listOf(
            ImageSliderVO(1, R.drawable.placeholder_show3),
            ImageSliderVO(2, R.drawable.placeholder_show2),
            ImageSliderVO(3, R.drawable.placeholder_show1),
        )
    }
}
