package com.example.practica9

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class ActivityRegistrar : AppCompatActivity() {

    lateinit var edUsuario:EditText
    lateinit var edPass1:EditText
    lateinit var edPass2:EditText
    lateinit var edNombre:EditText
    lateinit var edTelefono:EditText
    lateinit var edCarrera: EditText

    lateinit var txtUsuario:TextInputLayout
    lateinit var txtPass1:TextInputLayout
    lateinit var txtPass2:TextInputLayout
    lateinit var txtNombre:TextInputLayout
    lateinit var txtTelefono:TextInputLayout
    lateinit var txtCarrera:TextInputLayout

    fun autentifica():Boolean{
        var res:Boolean = true
        val usuario:String = edUsuario.text.toString()
        val pass1:String = edPass1.text.toString()
        val pass2:String = edPass2.text.toString()
        val nombre:String = edNombre.text.toString()
        val telefono:String = edTelefono.text.toString()
        val carrera:String = edCarrera.text.toString()

        if(usuario.isNullOrEmpty()){
            txtUsuario.error = "Usuario vacio"
            res = false
        }else{
            txtUsuario.error = null
        }
        if(pass1.isNullOrEmpty()){
            txtPass1.error = "Contraseña vacia"
            res = false
        }else{
            txtPass1.error = null
        }
        if(pass2 != pass1){
            txtPass2.error  = "La contraseña es distinta"
            res = false
        }else{
            txtPass2.error  = null
        }
        if(nombre.isNullOrEmpty()){
            txtNombre.error = "El nombre está vacio"
            res = false
        }else{
            txtNombre.error = null
        }
        if(telefono.isNullOrEmpty()){
            txtTelefono.error = "Telefono vacio"
            res = false
        }else{
            txtTelefono.error = null
        }
        if(carrera.isNullOrEmpty()){
            txtCarrera.error = "Carrera vacia"
            res = false
        }else{
            txtCarrera.error = null
        }
        return res
    }

    fun guardar(v: View){
        if(autentifica()){
            val usuario:String = edUsuario.text.toString()
            val pass:String = edPass1.text.toString()
            val nombre:String = edNombre.text.toString()
            val telefono:String = edTelefono.text.toString()
            val carrera:String = edCarrera.text.toString()

            val preferencia = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
            val editor = preferencia.edit()
            editor.putString("usuario", usuario)
            editor.putString("pass", pass)
            editor.putString("nombre", nombre)
            editor.putString("telefono", telefono)
            editor.putString("carrera", carrera)
            editor.commit()
            Toast.makeText(this, "Se guardo el usuario", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)

        edUsuario = findViewById(R.id.edUsuario)
        edPass1 = findViewById(R.id.edPass1)
        edPass2 = findViewById(R.id.edPass2)
        edNombre = findViewById(R.id.edNombre)
        edTelefono = findViewById(R.id.edTelefono)
        edCarrera = findViewById(R.id.edCarrera)

        txtUsuario = findViewById(R.id.txtInputLayoutUsuario)
        txtPass1 = findViewById(R.id.txtInputLayoutPass1)
        txtPass2 = findViewById(R.id.txtInputLayoutPass2)
        txtNombre = findViewById(R.id.txtInputLayoutNombre)
        txtTelefono = findViewById(R.id.txtInputLayoutTelefono)
        txtCarrera = findViewById(R.id.txtInputLayoutCarrera)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}