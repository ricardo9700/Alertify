package mx.itesm.alertify;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.itesm.alertify.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReporteFrag extends Fragment {


    public ReporteFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reporte, container, false);
    }

}