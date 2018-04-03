package com.example.romanpuga.tfg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romanpuga on 14/12/17.
 */


public class SqlIO extends SQLiteOpenHelper{
    public static String DATABASE_NAME = "HERAADB";
    public static int DATABASE_VERSION = 1;

    public SqlIO(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    // Creación de las tablas.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario" +
                    " (idUser text NOT NULL PRIMARY KEY," +
                    " nombre text NOT NULL," +
                    " fechaN text NOT NULL," +
                    " sexo text NOT NULL)");

            db.execSQL("CREATE TABLE IF NOT EXISTS medicion" +
                    "(idTest integer NOT NULL PRIMARY KEY," +
                    " fecha string NOT NULL," +
                    " idUsuario text NOT NULL)");
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    // Actualización de las tablas.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try{
            db.execSQL("DROP TABLE IF EXISTS usuario");
            db.execSQL("DROP TABLE IF EXISTS medicion");
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
        this.onCreate(db);
    }


    // Devuelve todos los Id de los usuarios almacenados.
    public List<String> getIdUsers(){
        ArrayList<String> users = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM usuario", null);

        if (cursor.moveToFirst()){
            do {
                users.add(new String(cursor.getString(0)));
            } while (cursor.moveToNext());
        }
        return users;
    }


    // Devuelve la información de un usuario.
    public Usuario getUserById(String idUser){
        Usuario usuario = null;
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM usuario WHERE idUsuario=?", new String[]{idUser});

        if(cursor.moveToFirst()){
            do {
                usuario = new Usuario(new String (cursor.getString(0)),
                        new String (cursor.getString(1)),
                        new String (cursor.getString(2)),
                        new String (cursor.getString(3)));

            } while (cursor.moveToNext());
        }
        return usuario;
    }


    // Inserta un usuario.
    public void insertUser(String idUser, String nombre, String sexo, String fechaN){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try{
            db.execSQL("INSERT INTO usuario(idUser, nombre, fechaN, sexo) VALUES(?,?,?,?)", new String[]{idUser, nombre, fechaN, sexo});

            db.setTransactionSuccessful();
        } finally{
            db.endTransaction();
        }
    }

}
