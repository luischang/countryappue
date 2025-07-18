package dev.lchang.countryappue.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScaffold(navController: NavController, content: @Composable () -> Unit){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text("Menu principal")
                HorizontalDivider()
                //Home navigation
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    }
                )
                //Permissions navigation
                NavigationDrawerItem(
                    label = { Text("Permissions") },
                    selected = false,
                    onClick = {
                        navController.navigate("permissions")
                        scope.launch { drawerState.close() }
                    }
                )

                //Favorites navigation
                NavigationDrawerItem(
                    label = { Text("Favorites") },
                    selected = false,
                    onClick = {
                        navController.navigate("favorites")
                        scope.launch { drawerState.close() }
                    }
                )

                //ApiFootball navigation
                NavigationDrawerItem(
                    label = { Text("Football") },
                    selected = false,
                    onClick = {
                        navController.navigate("football")
                        scope.launch { drawerState.close() }
                    }
                )

                //WebView navigation
                NavigationDrawerItem(
                    label = { Text("Web") },
                    selected = false,
                    onClick = {
                        navController.navigate("web")
                        scope.launch { drawerState.close() }
                    }
                )

                //Chat Gemini navigation
                NavigationDrawerItem(
                    label = { Text("Chat") },
                    selected = false,
                    onClick = {
                        navController.navigate("chat")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        //Topappbar
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Country App") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ){
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ){
            paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ){
                content()
            }
        }
    }

}