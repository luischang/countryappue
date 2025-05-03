package dev.lchang.countryappue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.lchang.countryappue.presentation.auth.LoginScreen
import dev.lchang.countryappue.presentation.auth.RegisterScreen
import dev.lchang.countryappue.presentation.home.CountryListScreen
import dev.lchang.countryappue.presentation.navigation.NavigationMenu
import dev.lchang.countryappue.ui.theme.CountryappueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryappueTheme {
                // A surface container using the 'background' color from the themeRegisterScreen()
                NavigationMenu()
            }
        }
    }
}

