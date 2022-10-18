package com.example.sqlliveapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.prefs.PreferencesFactory

class AdminSqLite(Contex:Context?,
                  name: String,
                  factory: SQLiteDatabase.CursorFactory?,
                  version:Int): SQLiteOpenHelper(Contex,name, factory, version) {

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        //creando base de dato
        baseDeDatos?.execSQL("create table articulos(codigo int primary key,descripcion text, precio real)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}