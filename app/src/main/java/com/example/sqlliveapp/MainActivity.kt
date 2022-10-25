package com.example.sqlliveapp

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etnCodigo: EditText
    private lateinit var etnDescripcion:EditText
    private lateinit var etnPrecio:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etnCodigo = findViewById(R.id.etn_Codigo)
        etnDescripcion = findViewById(R.id.etn_Descripcion)
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
        val descripcion = etnDescripcion.text.toString()
        val precio = etnPrecio.text.toString()


        if(!codigo.isEmpty() &&!descripcion.isEmpty()){
            val registro = ContentValues()
            registro.put("codigo",codigo)
            registro.put("descripcion",descripcion)
            registro.put("precio", precio)

            baseDeDatos.insert("articulos", null,registro)
            baseDeDatos.close()


            etnCodigo.setText("")
            etnDescripcion.setText("")
            etnPrecio.setText("")

            Toast.makeText(this, "Registro exitoso",Toast.LENGTH_SHORT).show()


        }else{

            Toast.makeText(this,"Debe completar todos los campos para registrarse", Toast.LENGTH_SHORT).show()
        }
    }

    fun Buscar(v:View){
        // Se crea la conexion a la base de datos
        val admin = AdminSqLite(this,  "Tienda", null, 1)
        val dataBase:SQLiteDatabase = admin.writableDatabase

        val codigo = etnCodigo.text.toString()

        if (!codigo.isEmpty()){
            val fila = dataBase.rawQuery(
                "select descripcion, precio from articulos where codigo = $codigo", null
            )
            if (fila.moveToFirst()){
                etnDescripcion.setText(fila.toString())
            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_LONG).show()
                dataBase.close()
            }
        }else{
            Toast.makeText(this, "Debe ingresar su codigo", Toast.LENGTH_LONG).show()
        }
    }
    fun Modificar(Vista:View){

        // Se crea la conexion a la base de datos
        // nombre de clase creada       nombramos base de datos
        val admin = AdminSqLite(this, "Tienda", null, version = 1)
        val baseDeDatos: SQLiteDatabase = admin.writableDatabase


  val codigo=etnCodigo.text.toString()
        val descripcion=etnDescripcion.text.toString()
        val precio=etnCodigo.text.toString()

  if(!codigo.isEmpty() && !descripcion.isEmpty()&& !precio.isEmpty()){

      val registro=ContentValues()

      registro.put("descripcion",descripcion)
      registro.put("precio",precio)


      val cantidad:Int= baseDeDatos.update("articulos", registro, "codigo=$codigo",null )
      baseDeDatos.close()


      if(cantidad==1){

          Toast.makeText(this, "Articulo modificado correctamente",Toast.LENGTH_SHORT).show()



      }else{

          Toast.makeText(this, "no existe el articulo",Toast.LENGTH_SHORT).show()

   baseDeDatos.close()
      }

      etnCodigo.setText("")

      etnDescripcion.setText("")
      etnPrecio.setText("")



      Toast.makeText(this, "registro exitoso",Toast.LENGTH_SHORT).show()


  }else{

      Toast.makeText(this, "debes llenar todos los campos",Toast.LENGTH_SHORT).show()


  }



    }
fun eliminar (Vista:View){

    // Se crea la conexion a la base de datos
    // nombre de clase creada       nombramos base de datos
    val admin = AdminSqLite(this, "Tienda", null, version = 1)
    val baseDeDatos: SQLiteDatabase = admin.writableDatabase



    val codigo =etnCodigo.text.toString()


    if(!codigo.isEmpty()){

        val cantidad:Int=baseDeDatos.delete("articulo", "codigo=${codigo}", null )
        baseDeDatos.close()
        etnCodigo.setText("")
        etnDescripcion.setText("")
        etnPrecio.setText("")


         if(cantidad==1){

             Toast.makeText(this, "articulo eliminado",Toast.LENGTH_SHORT).show()
             baseDeDatos.close()
         }else{

             Toast.makeText(this, "no existe el articulo  ",Toast.LENGTH_SHORT).show()
             baseDeDatos.close()
         }


    }else {
        Toast.makeText(this, "Debes ingresar un  articulo  ", Toast.LENGTH_SHORT).show()
        baseDeDatos.close()

    }

fun siguiente(Vista:View){


    val ventana=Intent(applicationContext,sharepreferenceApp::class.java)
    startActivity(ventana)

}

}


}