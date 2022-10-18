package com.example.sqlliveapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etnCodigo: EditText
    private lateinit var etDescripcion:EditText
    private lateinit var etnPrecio:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etnCodigo = findViewById(R.id.etn_Codigo)
        etDescripcion = findViewById(R.id.et_Descripcion)
        etnPrecio = findViewById(R.id.etn_Precio)
    }
    // funcion para registrar productos
    //mandar metodo a la vista
    fun registrar(V:View){

        // Se crea la conexion a la base de datos
        // nombre de clase creada       nombramos base de datos
        val admin =AdminSqLite(this,"Tienda", null, version = 1)
        val baseDeDatos:SQLiteDatabase =admin.writableDatabase



        val codigo = etnCodigo.text.toString()
        val descripcion = etDescripcion.text.toString()
        val precio = etnPrecio.text.toString()


        if(!codigo.isEmpty() &&!descripcion.isEmpty()){
            val registro = ContentValues()
            registro.put("codigo",codigo)
            registro.put("descripcion",descripcion)
            registro.put("precio", precio)

            baseDeDatos.insert("articulos", null,registro)
            baseDeDatos.close()


            etnCodigo.setText("")
            etDescripcion.setText("")
            etnPrecio.setText("")

            Toast.makeText(this, "Registro exitoso",Toast.LENGTH_SHORT).show()


        }else{

            Toast.makeText(this,"Debe completar todos los campos para registrarse", Toast.LENGTH_SHORT).show()
        }
    }
}