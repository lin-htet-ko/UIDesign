package com.linhtetko.uidesign.ui.vos

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.linhtetko.uidesign.R
import com.linhtetko.uidesign.ui.screens.Screen

data class BottomNavItemVO(
    val id: Int,
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: String,
    val isSelected: Boolean = false,
) {
    companion object {
        val bottomNavItems = listOf(
            BottomNavItemVO(
                id = 1,
                label = R.string.lbl_home,
                icon = R.drawable.ic_home,
                route = Screen.Home.route,
                isSelected = true
            ),
            BottomNavItemVO(
                id = 2,
                label = R.string.lbl_wallet,
                icon = R.drawable.ic_outline_wallet_24,
                route = Screen.Wallet.route
            ),
            BottomNavItemVO(
                id = 3,
                label = R.string.lbl_more,
                icon = R.drawable.ic_baseline_more_horiz_24,
                route = Screen.More.route
            ),
        )
    }
}
