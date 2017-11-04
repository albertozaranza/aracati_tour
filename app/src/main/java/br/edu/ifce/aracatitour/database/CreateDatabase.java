package br.edu.ifce.aracatitour.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alberto Zaranza on 09/10/2017.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    public CreateDatabase(Context context) {
        super(context, "aracatitour", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =

                "CREATE TABLE RESTAURANTE (" +
                "   ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NOME TEXT," +
                "   RUA TEXT," +
                "   NUMERO INTEGER," +
                "   BAIRRO TEXT," +
                "   CELULAR TEXT," +
                "   TELEFONE_FIXO TEXT," +
                "   IMAGEM INTEGER," +
                "   LATITUDE DOUBLE PRECISION," +
                "   LONGITUDE DOUBLE PRECISION" +
                ");";

        db.execSQL(query);

        query =

                "CREATE TABLE HOTEL (" +
                "   ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NOME TEXT," +
                "   RUA TEXT," +
                "   NUMERO INTEGER," +
                "   BAIRRO TEXT," +
                "   CELULAR TEXT," +
                "   TELEFONE_FIXO TEXT," +
                "   IMAGEM INTEGER," +
                "   LATITUDE DOUBLE PRECISION," +
                "   LONGITUDE DOUBLE PRECISION" +
                ");";

        db.execSQL(query);

        query =

                "CREATE TABLE PONTO_HISTORICO (" +
                "   ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NOME TEXT," +
                "   RUA TEXT," +
                "   NUMERO INTEGER," +
                "   BAIRRO TEXT," +
                "   CELULAR TEXT," +
                "   TELEFONE_FIXO TEXT," +
                "   IMAGEM INTEGER," +
                "   LATITUDE DOUBLE PRECISION," +
                "   LONGITUDE DOUBLE PRECISION" +
                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

}
