package br.edu.ifce.aracatitour.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifce.aracatitour.R;

public class DefaultActivity extends AppCompatActivity {

    private int ID_LISTA;
    private String TABELA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        TextView nome = (TextView) findViewById(R.id.txv_nome);
        TextView rua = (TextView) findViewById(R.id.txv_rua);
        TextView numero = (TextView) findViewById(R.id.txv_numero);
        TextView bairro = (TextView) findViewById(R.id.txv_bairro);
        TextView celular = (TextView) findViewById(R.id.txv_celular);
        TextView telefone = (TextView) findViewById(R.id.txv_telefone);

        ImageView imagem = (ImageView) findViewById(R.id.imageView);


        try {
            Bundle extra = getIntent().getExtras();

            if (extra != null) {
                ID_LISTA = Integer.parseInt(extra.getString("ID_LISTA"));
                TABELA = extra.getString("TABELA");
            }

            SQLiteDatabase db = openOrCreateDatabase("aracatitour", MODE_PRIVATE, null);

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA + " WHERE ID = " + ID_LISTA + "", null);

            cursor.moveToFirst();

            while (cursor != null) {
                nome.setText(cursor.getString(cursor.getColumnIndex("NOME")));
                rua.setText(cursor.getString(cursor.getColumnIndex("RUA")));
                numero.setText(cursor.getString(cursor.getColumnIndex("NUMERO")));
                bairro.setText(cursor.getString(cursor.getColumnIndex("BAIRRO")));
                celular.setText(cursor.getString(cursor.getColumnIndex("CELULAR")));
                telefone.setText(cursor.getString(cursor.getColumnIndex("TELEFONE_FIXO")));
                //imagem.setImageResource(cursor.getInt(cursor.getColumnIndex("IMAGEM")));
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
