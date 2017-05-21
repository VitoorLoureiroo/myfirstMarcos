package com.example.vitor.myfirstmarcos.DB;

/**
 * Created by vitor on 19/05/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HipotecaDBHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "HipotecaDb";
    private static CursorFactory factory = null;

    public HipotecaDBHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL("CREATE TABLE HIPOTECA(" +
                " _id INTEGER PRIMARY KEY," +
                " hip_nome TEXT NOT NULL, " +
                " hip_condicoes TEXT, " +
                " hip_contato TEXT," +
                " hip_email TEXT, " +
                " hip_observacoes TEXT," +
                " hip_telefone TEXT)" );

        db.execSQL("CREATE UNIQUE INDEX hip_nome ON HIPOTECA(hip_nome ASC)");

        Log.i(this.getClass().toString(), "Tabla HIPOTECA creada");

		/*
         * Insertamos datos iniciales
		 */
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(1,'Santander')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(2,'BBVA')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(3,'La Caixa')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(4,'Cajamar')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(5,'Bankia')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(6,'Banco Sabadell')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nome) VALUES(7,'Banco Popular')");

        Log.i(this.getClass().toString(), "Datos iniciales HIPOTECA insertados");

        Log.i(this.getClass().toString(), "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == 2){
            db.execSQL("UPDATE HIPOTECA SET hip_contato = 'Julián Gómez Martínez'," +
                    "              hip_email = 'jgmartinez@gmail.com'," +
                    "              hip_observacoes = 'Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones'" +
                    " WHERE _id = 1");
        }

    }
}