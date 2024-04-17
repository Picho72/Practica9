package com.example.practica9

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    var usuario: String = ""
    var pass: String = ""
    var sesion:Boolean = false
    lateinit var txtUsuario:EditText
    lateinit var txtPass:EditText
    lateinit var btnEntrar:Button
    lateinit var txtInputLayoutUsuario:TextInputLayout
    lateinit var txtInputLayoutPass:TextInputLayout

    fun registrarNuevo(v:View){
        val intent = Intent(this, ActivityRegistrar::class.java)
        startActivity(intent)
    }

    fun autentificar(v: View){
        val u = txtUsuario.text.toString()
        val p = txtPass.text.toString()
        //Esto es provicional

        if(u.isNullOrEmpty()){
            txtInputLayoutUsuario.error = "Usuario vacio"
        }else{
            txtInputLayoutUsuario.error = null
        }
        if(p.isNullOrEmpty()){
            txtInputLayoutPass.error = "Contraseña vacia"
        }else{
            txtInputLayoutPass.error = null
        }

        if(!this.usuario.equals("xxx")&&
            (u.equals(this.usuario) && p.equals(this.pass)) &&
            !(u.isNullOrEmpty() || p.isNullOrEmpty())){
            //Se autentifica
            val intent = Intent(this, ActivityMostrar::class.java)
            startActivity(intent)
            //Escribo sesion en true
            val preferencia = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
            val editor = preferencia.edit()
            editor.putBoolean("sesion", true)
            editor.commit()
        }else{
            Toast.makeText(this, "Usuario y contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val preferencias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        usuario = preferencias.getString("usuario","xxx").toString()
        pass = preferencias.getString("pass", "xxx").toString()
        sesion = preferencias.getBoolean("sesion", false)

        if(sesion == true){
            val intent = Intent(this, ActivityMostrar::class.java)
            startActivity(intent)
        }


        txtUsuario = findViewById(R.id.edUsuario)
        txtPass = findViewById(R.id.txtPass)
        btnEntrar = findViewById(R.id.btnEntrar)
        txtInputLayoutUsuario = findViewById(R.id.txtInputLayoutUsuario)
        txtInputLayoutPass = findViewById(R.id.txtInputLayoutPass)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        val preferencias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        usuario = preferencias.getString("usuario","xxx").toString()
        pass = preferencias.getString("pass", "xxx").toString()
        sesion = preferencias.getBoolean("sesion", false)
    }
}