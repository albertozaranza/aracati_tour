package br.edu.ifce.aracatitour.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Alberto Zaranza on 09/10/2017.
 */

public class ManageDatabase {

    private static CreateDatabase createDatabase = null;

    public ManageDatabase(Context context){
        if(createDatabase == null){
            createDatabase = new CreateDatabase(context);
        }
    }

    public void addItem(){
        SQLiteDatabase db = createDatabase.getWritableDatabase();

        //Restaurantes
        String sql = "INSERT INTO RESTAURANTE (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Chaparral', 'Rua 1', 100, 'Dique', '(88)98888-8888', '(88)3421-2000', null, 1, 1)";
        db.execSQL(sql);
        sql = "INSERT INTO RESTAURANTE (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Xurupita', 'Rua 2', 200, 'Centro', '(88)99999-8888', '(88)3421-1000', null, 2, 1)";
        db.execSQL(sql);

        //Hoteis
        sql = "INSERT INTO HOTEL (NOME, RUA, NUMERO, BAIRRO, CELULAR, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Bons Ventos', 'Rua 6', 600, 'Centro', '(88)89999-1111', null, 3, 1)";
        db.execSQL(sql);

        //Pontos
        sql = "INSERT INTO PONTO_HISTORICO (NOME, RUA, NUMERO, BAIRRO, CELULAR, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Museu', 'Rua 11', 1100, 'Centro', '(88)98765-4321', null, 4, 1)";
        db.execSQL(sql);
    }

    public ArrayList<String> getAllItens(String tabela, String atributo){
        SQLiteDatabase db = createDatabase.getReadableDatabase();
        String sql = "SELECT " + atributo + " FROM " + tabela + "";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> itens = null;

        if(cursor != null && cursor.moveToFirst()){
            itens = new ArrayList<String>();

            do{
                itens.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        return itens;
    }

}
