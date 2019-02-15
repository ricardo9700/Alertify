package com.example.ricar.alertify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class InicioActiv extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction transaction=fm.beginTransaction();
            switch (item.getItemId()) {
                //REEMPLAZAR CODIGO CON EL DEL FRAGMENTO PROPIO
                case R.id.navigation_inicio:
                    mTextMessage.setText(R.string.title_inicio);
                    return true;
                case R.id.navigation_mapa:
                    mTextMessage.setText(R.string.title_mapa);
                    return true;
                case R.id.navigation_reportes:
                    mTextMessage.setText(R.string.title_reportes);
                    return true;
                case R.id.navigation_guia:
                    mTextMessage.setText(R.string.title_guia);
                    GuiaFrag fragGuia=new GuiaFrag();
                    transaction.replace(R.id.contFrag,fragGuia);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mTextMessage = (TextView) findViewById(R.id.tvTitle);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
