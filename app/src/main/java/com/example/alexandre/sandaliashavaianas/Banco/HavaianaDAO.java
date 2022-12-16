package com.example.alexandre.sandaliashavaianas.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilma on 05/11/2015.
 */
public class HavaianaDAO {

    HavaianaDbHelp mHelper;

    public HavaianaDAO(Context ctx) {

        mHelper = new HavaianaDbHelp(ctx);

    }

    public void inserir(Havaiana havaiana) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HavaianaDbHelp.COL_NOME, havaiana.nome);
        values.put(HavaianaDbHelp.COL_GTAFABRICANTE, havaiana.gtafabricante);
        values.put(HavaianaDbHelp.COL_VALOR, havaiana.valor);
        values.put(HavaianaDbHelp.COL_REF, havaiana.ref);
        values.put(HavaianaDbHelp.COL_CAPA, havaiana.capa);

        long id = db.insert(HavaianaDbHelp.TABELA_HAVAIANA, null, values);
        if (id == -1) {
            throw new RuntimeException("Erro ao inserir registro.");
        }
        db.close();

    }

    public void excluir(Havaiana havaiana) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(HavaianaDbHelp.TABELA_HAVAIANA,
                HavaianaDbHelp.COL_NOME + " = ? AND " + HavaianaDbHelp.COL_REF + " = ? ",
                new String[]{havaiana.nome, havaiana.ref});
        db.close();
    }

    public List<Havaiana> listar() {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "select * from " + HavaianaDbHelp.TABELA_HAVAIANA + " ORDER BY "
                        + HavaianaDbHelp.COL_NOME, null);

        int idx_Nome = cursor.getColumnIndex(HavaianaDbHelp.COL_NOME);
        int idx_GtaFabricante = cursor.getColumnIndex(HavaianaDbHelp.COL_GTAFABRICANTE);
        int idx_Valor = cursor.getColumnIndex(HavaianaDbHelp.COL_VALOR);
        int idx_Ref = cursor.getColumnIndex(HavaianaDbHelp.COL_REF);
        int idx_Capa = cursor.getColumnIndex(HavaianaDbHelp.COL_CAPA);

        List<Havaiana> havaianas = new ArrayList<>();

        while (cursor.moveToNext()) {
            String nome = cursor.getString(idx_Nome);
            String gtafabricante = cursor.getString(idx_GtaFabricante);
            String valor = cursor.getString(idx_Valor);
            String ref = cursor.getString(idx_Ref);
            String capa = cursor.getString(idx_Capa);

            Havaiana havaiana = new Havaiana(nome, gtafabricante,valor,ref, capa);
            havaianas.add(havaiana);
        }
        cursor.close();

        db.close();
        return havaianas;


    }

    public boolean isFavorito(Havaiana havaiana) {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "select _id from " + HavaianaDbHelp.TABELA_HAVAIANA + " WHERE "+
                HavaianaDbHelp.COL_NOME + " = ? AND " + HavaianaDbHelp.COL_REF + " = ? ",
                new String[]{havaiana.nome, havaiana.ref});

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;

    }
}
