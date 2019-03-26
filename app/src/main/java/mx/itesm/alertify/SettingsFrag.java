package mx.itesm.alertify;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import mx.itesm.alertify.BotonesySwitchesSettings;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFrag extends Fragment {

    private BotonesySwitchesSettings buttonAndSwitchesManager;

    private View view;

    public SettingsFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_settings,container,false);

        //Referencia a los campos de la pantalla
        buttonAndSwitchesManager = new BotonesySwitchesSettings();

        buttonAndSwitchesManager.setEts(view.findViewById(R.id.etNumeroPrincipal),"numberPrincipal");
        buttonAndSwitchesManager.setEts(view.findViewById(R.id.etNombrePrincipal),"namePrincipal");
        buttonAndSwitchesManager.setEts(view.findViewById(R.id.etNombre),"name");
        buttonAndSwitchesManager.setEts(view.findViewById(R.id.etNumero),"number");

        buttonAndSwitchesManager.setsCallOption(view.findViewById(R.id.call_contact),"contact");
        buttonAndSwitchesManager.setsCallOption(view.findViewById(R.id.call_911),"911");

        //SetChangeListener para los switches, si se llama a 911 no se puede llamar al mismo tiempo al contacto favorito
        buttonAndSwitchesManager.isCheck("contact");
        buttonAndSwitchesManager.isCheck("911");


        //Referencia y metodo onclick para el boton de Definir Contacto Principal y boton Añadir contacto
        buttonAndSwitchesManager.setButtons(view.findViewById(R.id.addPrincipContactButton),"contacPrincipal");

        buttonAndSwitchesManager.getButtons("addPrincipal").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonAndSwitchesManager.getEts("numberPrincipal").getText().toString().length()==0 || buttonAndSwitchesManager.getEts("namePrincipal").getText().toString().length()==0) {
                    Log.i("Error:", "falta un dato para registrarse");
                }

                else{
                    buttonAndSwitchesManager.getsCallOption("contact").setText("Llamar a "+buttonAndSwitchesManager.getEts("namePrincipal").getText().toString());
                    call();
                    Log.i("Numero: ", "" + buttonAndSwitchesManager.getEts("numberPrincipal").getText().toString() + "Nombre: " + buttonAndSwitchesManager.getEts("namePrincipal").getText().toString());
                }
            }
        });

        return view;
    }

    public void call()
    {
        final EditText phoneNumber = (EditText) view.findViewById(R.id.etNumeroPrincipal);
        String phoneNum = phoneNumber.getText().toString();
        if(!TextUtils.isEmpty(phoneNum)) {
            String dial = "tel:" + phoneNum;

//Make an Intent object of type intent.ACTION_CALL//

            startActivity(new Intent(Intent.ACTION_CALL,

//Extract the telephone number from the URI//

                    Uri.parse(dial)));
        }else {
            Toast.makeText(getActivity(), "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
        }

    }
}