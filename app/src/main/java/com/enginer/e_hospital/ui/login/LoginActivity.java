package com.enginer.e_hospital.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.enginer.e_hospital.AccessBase;
import com.enginer.e_hospital.MainActivity;
import com.enginer.e_hospital.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.sql.*;

public class LoginActivity extends AppCompatActivity {

    private String login,password;
    private EditText editTextlogin,editTextpassword;

    private Button loginButton;
    private ProgressBar loadingProgressBar;
    private AccessBase accessBase;
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Liaison entre variables et composants du desi
        editTextlogin = findViewById(R.id.username);
        editTextpassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        // Evenement du click button connexion
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessBase= new AccessBase();
                login = editTextlogin.getText().toString().trim();
                password = editTextpassword.getText().toString().trim();
                if (login.isEmpty() || password.isEmpty()){
                    String message = getString(R.string.error_fields);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
               else {
                   testLogin(login,password);

                }
                /*else{
                    String message = getString(R.string.error_connection);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
    public void testLogin(String logger, String passer){
        String emailrecup ="";
        String passrecup="";
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("chargement du pilote réussi");
            Connection co = DriverManager.getConnection("jdbc:mysql://10.156.83.142:3306/e_hospital","gaye95cheikh","27ndawGAYE");
            System.out.println("connexion réussi");
            Statement st = co.createStatement();
            ResultSet resultats = st.executeQuery("SELECT * FROM users WHERE email="+logger+" AND password="+passer);
            while (resultats.next()){
                 emailrecup = resultats.getString(2);
                 passrecup = resultats.getString(3);
            }
                if (emailrecup==logger && passrecup == passer){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("LOGIN", login);
                    startActivity(intent);
                }
                else {
                    String message = getString(R.string.error_connection);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                co.close();
        }
        catch (Exception se) {
            String message = getString(R.string.error_connection);
            Toast.makeText(LoginActivity.this, "Connection impossible", Toast.LENGTH_SHORT).show();
        }

    }
    public  void loginToServer(){
        String url = "http://10.156.83.142/connexion.php?email="+login+"&password="+password;
        OkHttpClient client = new  OkHttpClient();
        Request request = new  Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = getString(R.string.error_connection);
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    String result = response.body().string();
                    JSONObject jo = new JSONObject(result);
                    String status = jo.getString("status");
                    if (status.equalsIgnoreCase("ok")){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("LOGIN", login);
                        startActivity(intent);
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String message = getString(R.string.error_connection);
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

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