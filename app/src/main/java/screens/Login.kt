package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable

fun Login(navController: NavHostController) {

    Column {
        Text("esta es la pantalla del Login")
        Button(
            onClick = {
                navController.navigate("welcome")
            }
        ) {
            Text("volver")
        }
    }
}