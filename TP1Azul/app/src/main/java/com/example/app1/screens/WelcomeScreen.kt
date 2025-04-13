package com.example.app1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.app1.R  // Asegurate de importar el R para acceder a los recursos

@Composable
fun WelcomeScreen(userName: String) {
    // Estados para la selección de plataforma y preferencias
    var selectedPlatform by remember { mutableStateOf("") }
    var showOtherPreference by remember { mutableStateOf(false) }
    var otherPreference by remember { mutableStateOf("") }
    val preferencesOptions = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
    val selectedPreferences = remember { mutableStateMapOf<String, Boolean>() }

    // Inicializamos las preferencias en false
    preferencesOptions.forEach { option ->
        if (selectedPreferences[option] == null) {
            selectedPreferences[option] = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Bienvenido a la aplicación $userName", style = MaterialTheme.typography.headlineSmall)

        // Selección de plataforma
        Text(text = "Elige tu plataforma:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPlatform == "Android",
                onClick = { selectedPlatform = "Android" }
            )
            Text("Android", modifier = Modifier.padding(end = 8.dp))
            RadioButton(
                selected = selectedPlatform == "IOS",
                onClick = { selectedPlatform = "IOS" }
            )
            Text("IOS")
        }

        // Mostrar imagen del logo dependiendo de la plataforma seleccionada
        if (selectedPlatform.isNotEmpty()) {
            val imageRes = if (selectedPlatform == "Android") {
                R.drawable.ic_android  // Asegurate de que este recurso exista en res/drawable
            } else {
                R.drawable.ic_ios        // Asegurate de que este recurso exista en res/drawable
            }
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Logo de $selectedPlatform",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        // Selección de preferencias
        Text(text = "Selecciona tus preferencias:")
        preferencesOptions.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .toggleable(
                        value = selectedPreferences[option] ?: false,
                        onValueChange = {
                            selectedPreferences[option] = it
                            if (option == "Otra") {
                                showOtherPreference = it
                            }
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = selectedPreferences[option] ?: false, onCheckedChange = null)
                Text(text = option)
            }
        }
        if (showOtherPreference) {
            OutlinedTextField(
                value = otherPreference,
                onValueChange = { otherPreference = it },
                label = { Text("Otra preferencia") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
