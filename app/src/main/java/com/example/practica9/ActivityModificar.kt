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

class ActivityModificar : AppCompatActivity() {

    lateinit var edNombre:EditText
    lateinit var edTelefono:EditText
    lateinit var edCarrera:EditText

    lateinit var txtNombre: TextInputLayout
    lateinit var txtTelefono: TextInputLayout
    lateinit var txtCarrera: TextInputLayout

    fun valida():Boolean{
        var res:Boolean = true
        val nombre:String = edNombre.text.toString()
        val telefono:String = edTelefono.text.toString()
        val carrera:String = edCarrera.text.toString()

        if(nombre.isNullOrEmpty()){
            txtNombre.error = "Nombre vacio"
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

    fun guardar(v:View){
        if(valida()){
            val nombre:String = edNombre.text.toString()
            val telefono:String = edTelefono.text.toString()
            val carrera:String = edCarrera.text.toString()

            val preferencia = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
            val editor = preferencia.edit()
            editor.putString("nombre", nombre)
            editor.putString("telefono", telefono)
            editor.putString("carrera", carrera)
            editor.commit()
            Toast.makeText(this, "Modificacion exitosa", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun cancelar(v: View){
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar)

        edNombre = findViewById(R.id.edNombre)
        edTelefono = findViewById(R.id.edTelefono)
        edCarrera = findViewById(R.id.edCarrera)
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