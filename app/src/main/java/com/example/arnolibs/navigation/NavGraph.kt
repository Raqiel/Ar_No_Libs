package com.example.arnolibs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arnolibs.ui.Ar.ArScreen
import com.example.arnolibs.ui.Main.MainScreen

object Route{
    const val MAIN_SCREEN: String = "MAIN_SCREEN"
    const val AR_SCREEN: String = "AR_SCREEN"
}

sealed class Destination(){
    object UpPress: Destination()
    class Main(val route: String = Route.MAIN_SCREEN) : Destination()
    class Ar(val route: String = Route.AR_SCREEN) : Destination()

}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.Main().route
){

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            Route.MAIN_SCREEN
        ) {
            MainScreen(
                navigate = actions.navigate
            )
        }
        composable(
            Route.AR_SCREEN
        ) {
            ArScreen(
                navigate = actions.navigate
            )
        }
    }


}

class MainActions(private val navController: NavHostController) {
    val navigate: (Destination) -> Unit = { destination ->
        when (destination) {
            is Destination.UpPress -> navController.navigateUp()
            is Destination.Main -> navController.navigate(destination.route)
            is Destination.Ar -> navController.navigate(destination.route)
        }
    }

}
