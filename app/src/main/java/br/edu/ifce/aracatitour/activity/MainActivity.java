package br.edu.ifce.aracatitour.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifce.aracatitour.R;
import br.edu.ifce.aracatitour.database.ManageDatabase;
import br.edu.ifce.aracatitour.helper.Permissao;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences = null;
    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    ManageDatabase manageDatabase = new ManageDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.app_name);

        // TODO: 03/11/2017 OTIMIZAR AS LISTVIEW

        Permissao.validaPermissoes(this, permissoesNecessarias, 1);

        sharedPreferences = getSharedPreferences("first_run", MODE_PRIVATE);

        Button btnRestaurante = (Button) findViewById(R.id.btn_res);
        Button btnHotel = (Button) findViewById(R.id.btn_hot);
        Button btnPontosHistoricos = (Button) findViewById(R.id.btn_pnt);
        Button btnMapa = (Button) findViewById(R.id.btn_map);

        // TODO: 17/10/2017 INTERFACE COM BUSCA EM CADA LAYOUT

        btnRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("BUTTON", "1");
                startActivity(intent);
            }
        });

        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("BUTTON", "2");
                startActivity(intent);
            }
        });

        btnPontosHistoricos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("BUTTON", "3");
                startActivity(intent);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sharedPreferences.getBoolean("first_run", true)) {
            sharedPreferences.edit().putBoolean("first_run", false).commit();
            Toast.makeText(getApplicationContext(), "Criando banco", Toast.LENGTH_SHORT).show();
            manageDatabase.addItem();
            Toast.makeText(getApplicationContext(), "Banco criado", Toast.LENGTH_SHORT).show();
        }
    }
}
