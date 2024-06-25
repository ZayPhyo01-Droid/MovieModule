package com.ui.movie.navigation

import androidx.navigation.NavController
import com.core.navigation.GlobalNavigation

data class Navigation(
    val navController: NavController,
    val globalNavigation: GlobalNavigation = GlobalNavigationImpl(navController)
)
