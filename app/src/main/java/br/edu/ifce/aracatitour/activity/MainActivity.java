package br.edu.ifce.aracatitour.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        Permissao.validaPermissoes(this, permissoesNecessarias, 1);

        sharedPreferences = getSharedPreferences("first_run", MODE_PRIVATE);

        Button btnRestaurante = (Button) findViewById(R.id.btn_res);
        Button btnHotel = (Button) findViewById(R.id.btn_hot);
        Button btnPontosHistoricos = (Button) findViewById(R.id.btn_pnt);
        Button btnMapa = (Button) findViewById(R.id.btn_map);

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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int resultado : grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Permissões negadas");
                builder.setMessage("Para utilizar esse app, é necessário aceitar as permissões");
                builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
