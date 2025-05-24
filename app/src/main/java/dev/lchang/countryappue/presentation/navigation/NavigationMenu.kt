package dev.lchang.countryappue.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.lchang.countryappue.presentation.auth.LoginScreen
import dev.lchang.countryappue.presentation.auth.RegisterScreen
import dev.lchang.countryappue.presentation.favorites.FavoriteScreen
import dev.lchang.countryappue.presentation.home.CountryListScreen
import dev.lchang.countryappue.presentation.permissions.GalleryPermissionScreen

@Composable
fun NavigationMenu(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("register"){ RegisterScreen(navController)}
        composable("login"){ LoginScreen(navController)}


        //Navigation
        composable("home"){
            DrawerScaffold(navController) {
                CountryListScreen()
            }
        }

        composable("permissions"){
            DrawerScaffold(navController) {
               GalleryPermissionScreen()
            }
        }

        composable("favorites"){
            DrawerScaffold(navController) {
                FavoriteScreen()
            }
        }

    }

}