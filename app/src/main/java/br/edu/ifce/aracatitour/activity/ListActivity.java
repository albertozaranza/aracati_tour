package br.edu.ifce.aracatitour.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import br.edu.ifce.aracatitour.R;
import br.edu.ifce.aracatitour.database.ManageDatabase;

public class ListActivity extends AppCompatActivity {

    private int BUTTON;
    private String ID_LISTA;
    ManageDatabase manageDatabase = new ManageDatabase(this);
    ArrayList<String> itens;
    ArrayAdapter<String> adapter;
    ListView listaItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            Bundle extra = getIntent().getExtras();

            if(extra != null){
                BUTTON = Integer.parseInt(extra.getString("BUTTON"));
            }

            switch (BUTTON){
                case 1:

                    setSupportActionBar(toolbar);
                    toolbar.setTitle(R.string.restaurantes);

                    itens = manageDatabase.getAllItens("RESTAURANTE", "NOME");

                    adapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            itens
                    );

                    listaItens = (ListView) findViewById(R.id.listView);
                    listaItens.setAdapter(adapter);

                    listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent it = new Intent(ListActivity.this, DefaultActivity.class);
                            String itemLista = listaItens.getItemAtPosition(i).toString();
                            try{
                                SQLiteDatabase db = openOrCreateDatabase("aracati_tour", MODE_PRIVATE, null);

                                Cursor cursor = db.rawQuery("SELECT ID FROM RESTAURANTE WHERE NOME = '"
                                        + itemLista + "'", null);

                                cursor.moveToFirst();

                                while (cursor != null){
                                    ID_LISTA = cursor.getString(cursor.getColumnIndex("ID"));
                                    cursor.moveToNext();
                                }
                                cursor.close();
                                db.close();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            it.putExtra("ID_LISTA", ID_LISTA);
                            it.putExtra("TABELA", "RESTAURANTE");
                            startActivity(it);
                        }
                    });
                    break;

                case 2:

                    setSupportActionBar(toolbar);
                    toolbar.setTitle(R.string.hoteis);

                    itens = manageDatabase.getAllItens("HOTEL", "NOME");

                    adapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            itens
                    );

                    listaItens = (ListView) findViewById(R.id.listView);
                    listaItens.setAdapter(adapter);

                    listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent it = new Intent(ListActivity.this, DefaultActivity.class);
                            String itemLista = listaItens.getItemAtPosition(i).toString();
                            try{
                                SQLiteDatabase db = openOrCreateDatabase("aracati_tour", MODE_PRIVATE, null);

                                Cursor cursor = db.rawQuery("SELECT ID FROM HOTEL WHERE NOME = '"
                                        + itemLista + "'", null);

                                cursor.moveToFirst();

                                while (cursor != null){
                                    ID_LISTA = cursor.getString(cursor.getColumnIndex("ID"));
                                    cursor.moveToNext();
                                }
                                cursor.close();
                                db.close();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            it.putExtra("ID_LISTA", ID_LISTA);
                            it.putExtra("TABELA", "HOTEL");
                            startActivity(it);
                        }
                    });
                    break;

                case 3:

                    setSupportActionBar(toolbar);
                    toolbar.setTitle(R.string.pontos_historicos);

                    itens = manageDatabase.getAllItens("PONTO_HISTORICO", "NOME");

                    adapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            itens
                    );

                    listaItens = (ListView) findViewById(R.id.listView);
                    listaItens.setAdapter(adapter);

                    listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent it = new Intent(ListActivity.this, DefaultActivity.class);
                            String itemLista = listaItens.getItemAtPosition(i).toString();
                            try{
                                SQLiteDatabase db = openOrCreateDatabase("aracati_tour", MODE_PRIVATE, null);

                                Cursor cursor = db.rawQuery("SELECT ID FROM PONTO_HISTORICO WHERE NOME = '"
                                        + itemLista + "'", null);

                                cursor.moveToFirst();

                                while (cursor != null){
                                    ID_LISTA = cursor.getString(cursor.getColumnIndex("ID"));
                                    cursor.moveToNext();
                                }
                                cursor.close();
                                db.close();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            it.putExtra("ID_LISTA", ID_LISTA);
                            it.putExtra("TABELA", "PONTO_HISTORICO");
                            startActivity(it);
                        }
                    });
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
