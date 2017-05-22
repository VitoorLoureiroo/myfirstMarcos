package com.example.vitor.myfirstmarcos.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vitor on 19/05/2017.
 */

public class HipotecaDBAdapter {

    //NOME DA TABELA:
    private static final String C_TABLA = "HIPOTECA";

    //NOME DOS CAMPOS:
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_NOME = "hip_nome";
    public static final String C_COLUMNA_CONDICOES = "hip_condicoes";
    public static final String C_COLUMNA_CONTATO = "hip_contato";
    public static final String C_COLUMNA_EMAIL = "hip_email";
    public static final String C_COLUMNA_OBSERVACOES = "hip_observacoes";
    public static final String C_COLUMNA_TELEFONE = "hip_telefone";

    //ARRAY DE CAMPOS PARA AS CONSULTAS
    private String[] campos = new String[]{
            C_COLUMNA_ID,
            C_COLUMNA_NOME,
            C_COLUMNA_CONDICOES,
            C_COLUMNA_CONTATO,
            C_COLUMNA_EMAIL,
            C_COLUMNA_OBSERVACOES,
            C_COLUMNA_TELEFONE
    };

    private Context context;
    private HipotecaDBHelper hipotecaDBHelper;
    private SQLiteDatabase db;


    public HipotecaDBAdapter(Context context) {
        this.context = context;
    }

    public HipotecaDBAdapter abrir() throws SQLException {
        hipotecaDBHelper = new HipotecaDBHelper(context);
        db = hipotecaDBHelper.getWritableDatabase();
        return this;
    }

    public void fechar() {
        hipotecaDBHelper.close();
    }

    public Cursor getCursor() throws SQLException{
        Cursor cursor = db.query(true, C_TABLA, campos, null, null, null, null, null, null);
        return cursor;
    }

    public Cursor getRegistro(long id) throws SQLException{
        Cursor c = db.query(true, C_TABLA, campos, C_COLUMNA_ID + "=" + id, null, null, null, null, null);

        if (c != null){
            c.moveToFirst();
        }
        return c;
    }

    //insere na tabela
    public long insert(ContentValues reg)
    {
        if (db == null)
            abrir();
            return  db.insert(C_TABLA, null, reg);

    }
}
