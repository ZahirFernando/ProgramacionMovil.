package screens

import android.provider.Contacts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import components.contact

@Composable
fun ListItemExample() {
    val frutas = listOf("Manzana", "Pera", "Uva", "Naranja")

    val contacts = listOf(







    )
    LazyColumn {
        items(contacts) {
            contact ->
            Contact(contact.first, contact.second)

            Text(
                text = fruta,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun  ListItemExamplePreviw() {

    ListItemExample()
}
