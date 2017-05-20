package com.example.vitor.myfirstmarcos.DB;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.vitor.myfirstmarcos.R;

public class MainActivity extends ListActivity {

    public static final String C_MODO = "modo";
    public static final int C_VISUALIZAR = 551;

    private HipotecaDBAdapter dbAdapter;
    private Cursor cursor;
    private HipotecaCursorAdapter hipotecaAdapter;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(android.R.id.list);

        dbAdapter = new HipotecaDBAdapter(this);
        dbAdapter.abrir();

        consultar();
    }

    private void consultar() {

        cursor = dbAdapter.getCursor();
        startManagingCursor(cursor);
        hipotecaAdapter = new HipotecaCursorAdapter(this, cursor);
        lista.setAdapter(hipotecaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.hipoteca, menu);
        return true;

    }

    private void visualizar(long id){

        Intent i = new Intent(MainActivity.this, HipotecaFormulario.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(HipotecaDBAdapter.C_COLUMNA_ID, id);
        startActivityForResult(i, C_VISUALIZAR);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        visualizar(id);
    }


}
