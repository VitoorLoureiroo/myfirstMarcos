package com.example.vitor.myfirstmarcos.DB;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by vitor on 19/05/2017.
 */

public class HipotecaCursorAdapter extends CursorAdapter {

    TextView textView;

    private HipotecaDBAdapter hipotecaDBAdapter;

    public HipotecaCursorAdapter(Context context, Cursor c) {
        super(context, c);
        hipotecaDBAdapter = new HipotecaDBAdapter(context);
        hipotecaDBAdapter.abrir();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        //View view = (View) parent;
        //textView = (TextView) view;

        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        textView = (TextView) view;

        textView.setText(cursor.getString(cursor.getColumnIndex(HipotecaDBAdapter.C_COLUMNA_NOME)));


    }


}
