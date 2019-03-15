package mx.itesm.alertify;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import mx.itesm.alertify.R;

import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;
import static android.support.v4.content.ContextCompat.getSystemServiceName;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFrag extends Fragment implements OnMapReadyCallback {

    private static final int PERMISO_GPS = 200;
    private LocationManager gps;

    private GoogleMap mMap;

    public MapaFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.59, -99.22);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void onResume() {
        super.onResume();
        // Prueba si tiene permiso para acceder al gps
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // No lo tiene, solicitar el permiso
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISO_GPS);
            // Contestará con onRequestPermissionsResult...
        } else {
            // Ya tiene permiso, iniciar actualizaciones
            gps.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, (LocationListener) this);
        }
    }

    private void configurarGPS() {
        // Crea el administrador del gps
        gps = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        // Pregunta si está prendido el GPS en el sistema
        if (!gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Abrir Settings para prender el GPS, no se puede hacer con código
            prenderGPS();
        }
    }

    private void prenderGPS() {
        // El usuario lo debe encender, no se puede con programación
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("El GPS está apagado, ¿Quieres prenderlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new
                                Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)); // Abre settings
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISO_GPS && grantResults.length>0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Contestó que SI, otorga el permiso. Iniciar actualizaciones.
                gps.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, (LocationListener) this);
            } else {
                Log.i("onRequestPerm...","Contestó NO, indicarle cómo dar permiso...");
            }
        }
    }
}
