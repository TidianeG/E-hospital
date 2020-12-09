package com.enginer.e_hospital;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.enginer.e_hospital.ui.home.HomeViewModel;
import com.enginer.e_hospital.ui.login.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InscriptionFragment} factory method to
 * create an instance of this fragment.
 */
public class InscriptionFragment extends Fragment {
    private Button backPatient;
    private ScrollView layoutAddPatient;
    private DatePicker txtbirthday;
    private EditText txtfirstName,txtlastName,txtprofession,txtadress,txtemail,txtphone,txtlieu;
    private Button btnSave;
    private Spinner txtSpinnerGenre;
    private String firstName,lastName,profession,adress,email,phone,spinnerGenre,lieu,birthday;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inscription, container, false);
        backPatient = root.findViewById(R.id.backPatient);
        layoutAddPatient = root.findViewById(R.id.nav_inscription);
        /// affectation des variabes aux champs
        txtfirstName = root.findViewById(R.id.txtFirstName);
        txtlastName = root.findViewById(R.id.txtLastName);
        txtbirthday =(DatePicker) root.findViewById(R.id.textBirthday);
        txtprofession = root.findViewById(R.id.txtProfession);
        txtadress = root.findViewById(R.id.txtAdresse);
        txtemail = root.findViewById(R.id.textEmail);
        txtphone = root.findViewById(R.id.txtPhone);
        txtlieu = root.findViewById(R.id.txtLieu);
        txtSpinnerGenre = root.findViewById(R.id.spinnerGenre);

        btnSave = root.findViewById(R.id.btnSave);
        /// Récupération des infos du patient
        //// Click sur le boutton sauvegarder l'inscription du patient
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = txtfirstName.getText().toString().trim();
                lastName = txtlastName.getText().toString().trim();
                birthday =  txtbirthday.getDayOfMonth()+"/"+ (txtbirthday.getMonth() + 1)+"/"+txtbirthday.getYear();
                profession = txtprofession.getText().toString().trim();
                adress = txtadress.getText().toString().trim();
                email = txtemail.getText().toString().trim();
                phone = txtphone.getText().toString().trim();
                lieu = txtlieu.getText().toString().trim();
                spinnerGenre = txtSpinnerGenre.getSelectedItem().toString();
                    inscriptionToServer();

            }
        });
        backPatient.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                layoutAddPatient.setVisibility(View.INVISIBLE);
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.containerPatientList, new PatientManagementFragment());
                ft.commit();
            }
        });
        return root;
    }
    public void inscriptionToServer(){
        String url="http://10.156.83.142/ehospital/inscription.php";

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("nom", lastName)
                .add("prenom", firstName)
                .add("genre", spinnerGenre)
                .add("birthday", birthday)
                .add("profession", profession)
                .add("address", adress)
                .add("email", email)
                .add("phone", phone)
                .add("lieu", lieu)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
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
                    String status = jo.getString("status");
                    if (status.equalsIgnoreCase("ok")){

                        Toast.makeText(getActivity(), "Patient ajouté", Toast.LENGTH_SHORT).show();
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