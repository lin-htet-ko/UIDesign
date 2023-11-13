package com.linhtetko.uidesign.ui.vos

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.linhtetko.uidesign.R

data class PrimaryActionVO(
    val id: Int,
    @DrawableRes val image: Int,
    @StringRes val label: Int
){
    companion object{
        val actions = listOf(
            PrimaryActionVO(1, R.drawable.ic_map, R.string.lbl_map),
            PrimaryActionVO(2, R.drawable.ic_inhabitants, R.string.lbl_inhabitants),
            PrimaryActionVO(3, R.drawable.ic_shows, R.string.lbl_shows),
            PrimaryActionVO(4, R.drawable.ic_shopping, R.string.lbl_shopping),
            PrimaryActionVO(5, R.drawable.ic_dinning, R.string.lbl_dinning),
            PrimaryActionVO(6, R.drawable.ic_meet_chat, R.string.lbl_meet_chat),
        )
    }
}
