package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun SignUp(navController: NavHostController) {

    Column{
        Text("esta es la pantalla de Crear cuenta")
        Button(
            onClick = {
                navController.navigate("welcome")
            }
        ) {
            Text("volver")
        }
    }
}