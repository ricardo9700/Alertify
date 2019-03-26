package mx.itesm.alertify;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class BotonesySwitchesSettings {

    private Button addPrincipalContact;
    private Button addContact;
    private Switch sCallContact;
    private Switch sCall911;
    private EditText etNumberPrincipal;
    private EditText etNombrePrincipal;
    private EditText etNombre;
    private EditText etNumber;

    public void setsCallOption(View view, String name) {
        if (name == "contact") {
            sCallContact = (Switch) view;
        } else if (name == "911") {
            sCall911 = (Switch) view;
        } else {
            Log.i("ERROR", "Hubo un error");
        }
    }

    public Switch getsCallOption(String name) {
        if (name == "contact") {
            return sCallContact;
        } else if (name == "911") {
            return sCall911;
        } else {
            return null;
        }
    }

    public void setEts(View view, String name) {
        if (name == "numberPrincipal") {
            etNumberPrincipal = (EditText) view;
        } else if (name == "namePrincipal") {
            etNombrePrincipal = (EditText) view;
        } else if (name == "name") {
            etNombre = (EditText) view;
        } else if (name == "number") {
            etNumber = (EditText) view;
        }
    }

    public EditText getEts(String name) {
        if (name == "numberPrincipal") {
            return etNumberPrincipal;
        } else if (name == "namePrincipal") {
            return etNombrePrincipal;
        } else if (name == "name") {
            return etNombre;
        } else if (name == "number") {
            return etNumber;
        } else {
            return null;
        }
    }

    public void setButtons(View view,String name){
        if (name=="contacPrincipal"){
            addPrincipalContact=(Button)view;
        }

        else if(name=="addContacto"){

        }
    }

    public void isCheck(String name) {
        if (name == "contact") {
            getsCallOption("contact").setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (getsCallOption("contact").isChecked()) {
                        Log.i("SE LLAMARA A", " " + getEts("namePrincipal").getText().toString().length());
                        getsCallOption("contact").setChecked(true);
                        getsCallOption("911").setChecked(false);
                    }
                }
            });

        } else if (name == "911") {
            getsCallOption("911").setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (getsCallOption("911").isChecked()) {
                        Log.i("SE LLAMARA A", "911");
                        getsCallOption("911").setChecked(true);
                        getsCallOption("contact").setChecked(false);
                    }
                }
            });
        }
    }

    public Button getButtons(String name) {
        if(name=="addPrincipal"){
            return addPrincipalContact;
        }

        else return null;
    }
}