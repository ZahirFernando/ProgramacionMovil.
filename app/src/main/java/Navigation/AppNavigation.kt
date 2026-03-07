package Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.Login
import screens.SignUp


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ){
        composable("welcome"){
            Login(navController)
        }
        composable ("SignUp"){
            SignUp(navController)
        }
    }
}


