package br.edu.ifce.aracatitour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    ManageDatabase manageDatabase = new ManageDatabase(this);
    ArrayList<String> itens;
    ArrayAdapter<String> adapter;
    ListView listaItens;
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        try{
            Bundle extra = getIntent().getExtras();

            if(extra != null){
                BUTTON = Integer.parseInt(extra.getString("BUTTON"));
            }

            switch (BUTTON){
                case 1:

                    /*toolbar.setTitle(R.string.restaurantes);
                    setSupportActionBar(toolbar);*/

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
                            it.putExtra("ID_LISTA", Integer.toString(i+1));
                            it.putExtra("TABELA", "RESTAURANTE");
                            startActivity(it);
                        }
                    });
                    break;

                case 2:

                    /*toolbar.setTitle(R.string.hoteis);
                    setSupportActionBar(toolbar);*/

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
                            it.putExtra("ID_LISTA", Integer.toString(i+1));
                            it.putExtra("TABELA", "HOTEL");
                            startActivity(it);
                        }
                    });
                    break;

                case 3:

                    /*toolbar.setTitle(R.string.pontos_historicos);
                    setSupportActionBar(toolbar);*/

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
                            it.putExtra("ID_LISTA", Integer.toString(i+1));
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
