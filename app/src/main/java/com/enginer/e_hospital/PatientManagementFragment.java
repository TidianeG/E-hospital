package com.enginer.e_hospital;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.enginer.e_hospital.ui.home.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class PatientManagementFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private Button btnNewPatient,btnUpdate;
    private LinearLayout layout;
    private TableLayout tableLayout;
    private TableRow tableRow;
    private TextView numP,nomP,prenomP,phoneP,adresseP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_management, container, false);
        btnNewPatient = view.findViewById(R.id.btnNewPatient);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        layout = view.findViewById(R.id.containerListPatient);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        tableLayout = view.findViewById(R.id.tableLayout);
        ///Method listerPatient



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listerPatient();
            }
        });

        btnNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.INVISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.containerPatientList, new InscriptionFragment()).addToBackStack(null).commit();
            }
        });
        listerPatient();
        return view;
    }
    public TextView generateTextView(String texte, LayoutParams ly) {
        TextView result = new TextView(getContext());
        result.setBackgroundColor(Color.LTGRAY);
        result.setTextColor(Color.DKGRAY);
        result.setGravity(Gravity.CENTER);
        result.setPadding(2, 2, 2, 2);
        result.setText(texte);
        result.setTextSize(20);
        result.setVisibility(View.VISIBLE);
        result.setLayoutParams(ly);
        return result;
    }
    public void listerPatient(){
        String url="http://192.168.43.135/affiche_patient.php";

        OkHttpClient client = new  OkHttpClient();
        Request request = new  Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = getString(R.string.error_inscription);
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string();
                    JSONObject jo = new JSONObject(result);

                    if (jo.length()>0){

                        for (int i=0;i<jo.getJSONArray("patients").length();i++){

                            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                                    LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(2, 2, 2, 2);
                                tableRow = new TableRow(getContext());
                                tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                                        LayoutParams.WRAP_CONTENT));
                                tableRow.addView(generateTextView(jo.getJSONArray("patients").getJSONObject(i).getString("num_patient"), layoutParams));
                                tableRow.addView(generateTextView(jo.getJSONArray("patients").getJSONObject(i).getString("nom_patient"), layoutParams));
                                tableRow.addView(generateTextView(jo.getJSONArray("patients").getJSONObject(i).getString("prenom_patient"), layoutParams));
                                tableRow.addView(generateTextView(jo.getJSONArray("patients").getJSONObject(i).getString("adresse_patient"), layoutParams));
                                tableRow.addView(generateTextView(jo.getJSONArray("patients").getJSONObject(i).getString("telephone_patient"), layoutParams));
                                tableLayout.addView(tableRow, layoutParams);

                        }

                    }
                    else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                String message = getString(R.string.error_inscription);
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}