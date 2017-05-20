package com.example.vitor.myfirstmarcos.DB;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.vitor.myfirstmarcos.R;

/**
 * Created by vitor on 19/05/2017.
 */

public class Hipoteca extends ListActivity {

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
    }
}
