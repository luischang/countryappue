package dev.lchang.countryappue.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lchang.countryappue.presentation.auth.LoginScreen
import dev.lchang.countryappue.presentation.auth.RegisterScreen
import dev.lchang.countryappue.presentation.home.CountryListScreen

@Composable
fun NavigationMenu(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register"){
        composable("register"){ RegisterScreen(navController)}
        composable("login"){ LoginScreen(navController)}
        composable("home"){ CountryListScreen()}

    }

}