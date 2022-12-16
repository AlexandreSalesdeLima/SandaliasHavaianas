package com.example.alexandre.sandaliashavaianas.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wilma on 05/11/2015.
 */
public class HavaianaDbHelp extends SQLiteOpenHelper {

    public static final String NOME_BANCO        = "bcoHavaiana";
    public static final int VERSAO_BANCO         = 1;

    public static final String TABELA_HAVAIANA   = "havaiana";
    public static final String  COL_ID           = "_id" ;
    public static final String COL_NOME          = "nome";
    public static final String COL_GTAFABRICANTE  = "gtafabricante";
    public static final String COL_VALOR = "valor";
    public static final String COL_REF  = "ref";
    public static final String COL_CAPA = "capa";

    public HavaianaDbHelp(Context ctx){
        super(ctx, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABELA_HAVAIANA +"(" +
                COL_ID          +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COL_NOME        +" TEXT NOT NULL, " +
                COL_GTAFABRICANTE+" TEXT NOT NULL, "+
                COL_VALOR+" TEXT NOT NULL, "+
                COL_REF+" TEXT NOT NULL, "+
                COL_CAPA      +" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
