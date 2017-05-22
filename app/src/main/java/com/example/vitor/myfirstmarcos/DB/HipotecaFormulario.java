package com.example.vitor.myfirstmarcos.DB;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText observacoes;

    private Button button_salvar;
    private Button button_cancelar;

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
        observacoes = (EditText)findViewById(R.id.observacoes);

        button_salvar = (Button)findViewById(R.id.button_salvar);
        button_cancelar = (Button)findViewById(R.id.button_cancelar);

        dbAdapter = new HipotecaDBAdapter(this);
        dbAdapter.abrir();

        //pegando o id se vier
        if (extra.containsKey(HipotecaDBAdapter.C_COLUMNA_ID)){
            id = extra.getLong(HipotecaDBAdapter.C_COLUMNA_ID);
            consultar(id);
        }

        //estabelecer modo do forumlario
        estabelecerModo(extra.getInt(MainActivity.C_MODO));

        //definir acoes p os botoes
        button_salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                salvar();
            }
        });

        button_cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                cancelar();
            }

        });

    }

    private void estabelecerModo(int m){
        this.modo = m;
        if (modo == MainActivity.C_VISUALIZAR){
            this.setTitle(nome.getText().toString());
            this.setEdicion(false);
        } else if (modo == MainActivity.C_CREAR){
            this.setTitle(R.string.hipoteca_crear_titulo);
            this.setEdicion(true);
        }
    }

    private void consultar(long id){
        cursor = dbAdapter.getRegistro(id);

        //retrieve dos valores
        nome.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_NOME)));
        condicoes.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_CONDICOES)));
        contato.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_CONTATO)));
        telefone.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_TELEFONE)));
        email.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_EMAIL)));
        observacoes.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_OBSERVACOES)));

    }

    private void setEdicion(boolean option){
        nome.setEnabled(option);
        condicoes.setEnabled(option);
        contato.setEnabled(option);
        telefone.setEnabled(option);
        email.setEnabled(option);
        observacoes.setEnabled(option);
    }

    private void salvar(){
        ContentValues reg = new ContentValues();

        reg.put(HipotecaDBAdapter.C_COLUMNA_NOME, nome.getText().toString());
        reg.put(HipotecaDBAdapter.C_COLUMNA_CONDICOES, condicoes.getText().toString());
        reg.put(HipotecaDBAdapter.C_COLUMNA_CONTATO, contato.getText().toString());
        reg.put(HipotecaDBAdapter.C_COLUMNA_TELEFONE, telefone.getText().toString());
        reg.put(HipotecaDBAdapter.C_COLUMNA_EMAIL, email.getText().toString());
        reg.put(HipotecaDBAdapter.C_COLUMNA_OBSERVACOES, observacoes.getText().toString());

        if (modo == MainActivity.C_CREAR)
        {
            dbAdapter.insert(reg);
            Toast.makeText(HipotecaFormulario.this, R.string.hipoteca_criar_confirmacao, Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK);
        finish();
    }

    private void cancelar(){
        setResult(RESULT_CANCELED, null);
        finish();
    }

}
