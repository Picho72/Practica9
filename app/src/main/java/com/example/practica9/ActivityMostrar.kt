package com.example.practica9

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityMostrar : AppCompatActivity() {

    var nombre:String = ""
    var telefono:String = ""
    var carrera:String = ""

    lateinit var txtNombre:TextView
    lateinit var txtTelefono:TextView
    lateinit var txtCarrera:TextView

    fun cerrar(v: View){
        //Escribo sesion en false
        val preferencia = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val editor = preferencia.edit()
        editor.putBoolean("sesion", false)
        editor.commit()
        finish()
    }

    fun modificar(v:View){
        val intent = Intent(this, ActivityModificar::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar)

        txtNombre = findViewById<TextView>(R.id.txtNombre)
        txtTelefono = findViewById<TextView>(R.id.txtTelefono)
        txtCarrera = findViewById<TextView>(R.id.txtCarrera)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val preferencias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        nombre = preferencias.getString("nombre", "xxx").toString()
        telefono = preferencias.getString("telefono", "xxx").toString()
        carrera = preferencias.getString("carrera", "xxx").toString()

        txtNombre.text = nombre
        txtTelefono.text = telefono
        txtCarrera.text = carrera

    }

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        nombre = preferencias.getString("nombre", "xxx").toString()
        telefono = preferencias.getString("telefono", "xxx").toString()
        carrera = preferencias.getString("carrera", "xxx").toString()

        txtNombre.text = nombre
        txtTelefono.text = telefono
        txtCarrera.text = carrera
    }
}