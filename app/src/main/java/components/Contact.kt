package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pantalla.R

@Composable
fun contact() {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Foto de contacto",
            modifier = Modifier
                .size(64.dp)

        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = name,
                frontSize = 18.sp,
                dontWeight = FontWeight.Black
            )
            Text(
                text = phone,
                fontSize = 14.sp
            )

        }
    }
}