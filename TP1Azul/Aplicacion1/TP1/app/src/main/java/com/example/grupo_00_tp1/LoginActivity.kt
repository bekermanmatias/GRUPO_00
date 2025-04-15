package com.example.grupo_00_tp1
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUser = findViewById(R.id.editTextUser)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonLogin.setOnClickListener {
            val user = editTextUser.text.toString()
            val password = editTextPassword.text.toString()

            if (user == "Juan Torres" && password == "1234utn") {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                // Acá después vamos a ir a la pantalla de bienvenida
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        buttonRegister.setOnClickListener {
            Toast.makeText(this, "Registro no implementado aún", Toast.LENGTH_SHORT).show()
        }
    }
}
