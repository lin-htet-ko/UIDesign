package com.linhtetko.uidesign.ui.screens

sealed class Screen(val route: String){

    companion object{
        const val ROUTE_HOME = "/home"
        const val ROUTE_WALLET = "/wallet"
        const val ROUTE_MORE = "/more"
    }

    object Home: Screen(ROUTE_HOME)

    object Wallet: Screen(ROUTE_WALLET)

    object More: Screen(ROUTE_MORE)
}
