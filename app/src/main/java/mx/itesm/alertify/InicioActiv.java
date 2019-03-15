package mx.itesm.alertify;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import mx.itesm.alertify.R;

public class InicioActiv extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();

            switch (item.getItemId()) {
                //REEMPLAZAR CODIGO CON EL DEL FRAGMENTO PROPIO
                case R.id.navigation_inicio:
                    mTextMessage.setText(R.string.title_inicio);
                    BotonFrag fragBoton = new BotonFrag();
                    transaction.replace(R.id.contFrag,fragBoton);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_mapa:
                    mTextMessage.setText(R.string.title_mapa);
                    MapaFrag fragMapa = new MapaFrag();
                    transaction.replace(R.id.contFrag,fragMapa);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_reportes:
                    mTextMessage.setText(R.string.title_reportes);
                    ReporteFrag fragReporte = new ReporteFrag();
                    transaction.replace(R.id.contFrag,fragReporte);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_guia:
                    GuiaFrag fragGuia = new GuiaFrag();
                    transaction.replace(R.id.contFrag,fragGuia);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_settings:
                    SettingsFrag fragSettings = new SettingsFrag();
                    transaction.replace(R.id.contFrag,fragSettings);
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

        mTextMessage = (TextView) findViewById(R.id.et_Alertify);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mTextMessage.setText(R.string.title_inicio);
        BotonFrag fragBoton = new BotonFrag();
        transaction.replace(R.id.contFrag,fragBoton);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
