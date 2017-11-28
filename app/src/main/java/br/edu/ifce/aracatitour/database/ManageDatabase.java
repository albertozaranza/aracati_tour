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
                "VALUES ('Chaparral', 'Rua 1', 100, 'Dique', '(88) 98888-8888', '(88) 3421-1000', null, -4.5712094, -37.7788612)";
        db.execSQL(sql);
        sql = "INSERT INTO RESTAURANTE (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Talher de Prata', 'Rua 2', 200, 'Centro', '(88) 99999-8888', '(88) 3421-2000', null, -4.5722094, -37.7798612)";
        db.execSQL(sql);
        sql = "INSERT INTO RESTAURANTE (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Picanha no Ponto', 'Rua 3', 300, 'Centro', '(88) 94624-5233', '(88) 3421-3000', null, -4.5732094, -37.7808612)";
        db.execSQL(sql);
        sql = "INSERT INTO RESTAURANTE (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Super Grill', 'Rua 4', 200, 'Centro', '(88) 99999-8888', '(88) 3421-1000', null, -4.5742094, -37.7818612)";
        db.execSQL(sql);
        sql = "INSERT INTO RESTAURANTE (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Comida Caseira', 'Rua 5', 200, 'Centro', '(88) 99999-8888', '(88) 3421-1000', null, -4.5752094, -37.7828612)";
        db.execSQL(sql);

        //Hoteis
        sql = "INSERT INTO HOTEL (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Bons Ventos', 'Rua 6', 600, 'Centro', '(88) 94363-7344', '(88) 3421-2351', null, -4.5762094, -37.7838612)";
        db.execSQL(sql);
        sql = "INSERT INTO HOTEL (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Por do Sol', 'Rua 7', 700, 'Centro', '(88) 96422-6242', '(88) 3421-1538', null, -4.5772094, -37.7848612)";
        db.execSQL(sql);
        sql = "INSERT INTO HOTEL (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Sol Nascente', 'Rua 8', 800, 'Centro', '(88) 92646-5135', '(88) 3421-3158', null, -4.5782094, -37.7858612)";
        db.execSQL(sql);
        sql = "INSERT INTO HOTEL (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Bom Sono', 'Rua 9', 900, 'Centro', '(88) 97537-6243', '(88) 3421-8428', null, -4.5792094, -37.7868612)";
        db.execSQL(sql);
        sql = "INSERT INTO HOTEL (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Sono Eterno', 'Rua 10', 1000, 'Centro', '(88) 96246-8353', '(88) 3421-4291', null, -4.5802094, -37.7878612)";
        db.execSQL(sql);

        //Pontos
        sql = "INSERT INTO PONTO_HISTORICO (NOME, RUA, NUMERO, BAIRRO, CELULAR,  TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Museu', 'Rua 11', 1100, 'Centro', '(88) 98765-4321', '(88) 3421-0521', null, -4.5812094, -37.7888612)";
        db.execSQL(sql);
        sql = "INSERT INTO PONTO_HISTORICO (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Biblioteca', 'Rua 11', 1100, 'Centro', '(88) 98765-6422', '(88) 3421-4156', null, -4.5822094, -37.7898612)";
        db.execSQL(sql);
        sql = "INSERT INTO PONTO_HISTORICO (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Igreja', 'Rua 11', 1100, 'Centro', '(88) 98765-7533', '(88) 3421-5931', null, -4.5832094, -37.7908612)";
        db.execSQL(sql);
        sql = "INSERT INTO PONTO_HISTORICO (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Praça', 'Rua 11', 1100, 'Centro', '(88) 98765-8684', '(88) 3421-6223', null, -4.5842094, -37.7918612)";
        db.execSQL(sql);
        sql = "INSERT INTO PONTO_HISTORICO (NOME, RUA, NUMERO, BAIRRO, CELULAR, TELEFONE_FIXO, IMAGEM, LATITUDE, LONGITUDE) " +
                "VALUES ('Iglu', 'Rua 11', 1100, 'Centro', '(88) 98765-5232', '(88) 3421-0131', null, -4.5852094, -37.7928612)";
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
