package br.edu.ifce.aracatitour.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.edu.ifce.aracatitour.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private String nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        /*mMap.setMyLocationEnabled(true);*/

        LatLng aracati = new LatLng(-4.5612094, -37.7688612);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aracati, 15));

        mMap.addMarker(new MarkerOptions()
                .title("Aracati")
                .snippet("Terra dos Bons Ventos.")
                .position(aracati));

        getRestaurantes();
        getHoteis();
        getPontosHistoricos();

    }

    private void getRestaurantes(){
        try {
            SQLiteDatabase db = openOrCreateDatabase("aracatitour", MODE_PRIVATE, null);

            Cursor cursor = db.rawQuery("SELECT NOME, LATITUDE, LONGITUDE FROM RESTAURANTE", null);

            cursor.moveToFirst();

            while (cursor != null) {
                latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LATITUDE")));
                longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LONGITUDE")));
                nome = cursor.getString(cursor.getColumnIndex("NOME"));
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title(nome));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getHoteis(){
        try {
            SQLiteDatabase db = openOrCreateDatabase("aracatitour", MODE_PRIVATE, null);

            Cursor cursor = db.rawQuery("SELECT NOME, LATITUDE, LONGITUDE FROM HOTEL", null);

            cursor.moveToFirst();
            while (cursor != null) {
                latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LATITUDE")));
                longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LONGITUDE")));
                nome = cursor.getString(cursor.getColumnIndex("NOME"));
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title(nome));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getPontosHistoricos(){
        try {
            SQLiteDatabase db = openOrCreateDatabase("aracatitour", MODE_PRIVATE, null);

            Cursor cursor = db.rawQuery("SELECT NOME, LATITUDE, LONGITUDE FROM PONTO_HISTORICO", null);

            cursor.moveToFirst();

            while (cursor != null) {
                latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LATITUDE")));
                longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex("LONGITUDE")));
                nome = cursor.getString(cursor.getColumnIndex("NOME"));
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title(nome));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
