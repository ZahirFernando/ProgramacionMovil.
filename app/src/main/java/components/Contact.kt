package com.example.contactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Contacto(val nombre: String, val telefono: String)


val coloresFondo = listOf(
    Color(0xFFDDE8FF),
    Color(0xFFD6F5E3),
    Color(0xFFFFE8D6)
)
val coloresIcono = listOf(
    Color(0xFF2563EB),
    Color(0xFF16A34A),
    Color(0xFFEA580C)
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaContactos()
            }
        }
    }
}

@Composable
fun ItemContacto(contacto: Contacto, posicion: Int) {

    val indicador = posicion % 3

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Círculo de avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(coloresFondo[indicador]),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = coloresIcono[indicador],
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nombre y teléfono
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contacto.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = contacto.telefono,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.LightGray
        )
    }
}

@Composable
fun PantallaContactos() {

    val listaContactos = remember {
        mutableStateListOf(
            Contacto("Ana García", "+34 612 345 678"),
            Contacto("Carlos Rodríguez", "+34 699 888 777"),
            Contacto("Elena Martínez", "+34 655 443 322")
        )
    }

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var tabSeleccionado by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {

                NavigationBarItem(
                    selected = tabSeleccionado == 0,
                    onClick = { tabSeleccionado = 0 },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Contactos") },
                    label = { Text("Contactos") }
                )

                NavigationBarItem(
                    selected = tabSeleccionado == 1,
                    onClick = { tabSeleccionado = 1 },
                    icon = { Icon(Icons.Filled.Phone, contentDescription = "Recientes") },
                    label = { Text("Recientes") }
                )

                NavigationBarItem(
                    selected = tabSeleccionado == 2,
                    onClick = { tabSeleccionado = 2 },
                    icon = { Icon(Icons.Filled.Star, contentDescription = "Favoritos") },
                    label = { Text("Favoritos") }
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // Título
            Text(
                text = "Mis Contactos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Campo nombre
            Text(text = "Nombre", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = { Text("Ej: Juan Pérez") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Campo teléfono
            Text(text = "Teléfono", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                placeholder = { Text("+34 600 000 000") },
                leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            // Mensaje de error
            if (error.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = error, color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón agregar
            Button(
                onClick = {
                    if (nombre.isBlank() || telefono.isBlank()) {
                        error = "Por favor completa todos los campos."
                    } else {
                        listaContactos.add(Contacto(nombre.trim(), telefono.trim()))
                        nombre = ""
                        telefono = ""
                        error = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar Contacto", fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón limpiar
            OutlinedButton(
                onClick = {
                    nombre = ""
                    telefono = ""
                    error = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.Close, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Limpiar Campos", fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Título lista
            Text(
                text = "LISTA DE CONTACTOS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2563EB),
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de contactos
            LazyColumn {
                itemsIndexed(listaContactos) { index, contacto ->
                    ItemContacto(contacto = contacto, posicion = index)
                    HorizontalDivider(color = Color(0xFFF0F0F0))
                }
            }
        }
    }
}