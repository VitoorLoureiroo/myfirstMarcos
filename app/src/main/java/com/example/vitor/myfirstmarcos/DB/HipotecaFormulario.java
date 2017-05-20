package com.example.vitor.myfirstmarcos.DB;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import com.example.vitor.myfirstmarcos.R;

/**
 * Created by vitor on 19/05/2017.
 */

public class HipotecaFormulario extends Activity {

    private HipotecaDBAdapter dbAdapter;
    private Cursor cursor;

    private int modo; //modo do formulario
    private long id; //identificador do registro que se edita quando a opção é MODIFICAR

    //elementos da view
    private EditText nome;
    private EditText condicoes;
    private EditText contato;
    private EditText telefone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hipoteca_formulario);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if(extra == null) return;

        //obtendo os elementos da view
        nome = (EditText)findViewById(R.id.nome);
        condicoes = (EditText)findViewById(R.id.condicoes);
        contato = (EditText)findViewById(R.id.contato);
        telefone = (EditText)findViewById(R.id.telefone);
        email = (EditText)findViewById(R.id.email);

        dbAdapter = new HipotecaDBAdapter(this);
        dbAdapter.abrir();

        //pegando o id se vier
        if (extra.containsKey(HipotecaDBAdapter.C_COLUMNA_ID)){
            id = extra.getLong(HipotecaDBAdapter.C_COLUMNA_ID);
            consultar(id);
        }

        //estabelecer modo do forumlario
        estabelecerModo(extra.getInt(MainActivity.C_MODO));
    }

    private void estabelecerModo(int m){
        this.modo = m;
        if (modo == MainActivity.C_VISUALIZAR){
            this.setTitle(nome.getText().toString());
            this.setEdicion(false);
        }
    }

    private void consultar(long id){
        cursor = dbAdapter.getRegistro(id);

        //retrieve dos valores
        nome.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_NOME)));
        condicoes.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_CONDICOES)));
        contato.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_CONTATO)));
        telefone.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_CONTATO)));
        email.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_EMAIL)));

    }

    private void setEdicion(boolean option){
        nome.setEnabled(option);
        condicoes.setEnabled(option);
        contato.setEnabled(option);
        telefone.setEnabled(option);
        email.setEnabled(option);
    }

}
