package com.example.guillermorios.ingresoautos;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Inicio extends Activity {

    Button ingresar;
    EditText contraseña, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ingresar = (Button)findViewById(R.id.btningreso);
        contraseña = (EditText) findViewById(R.id.idcontraseña);
        correo = (EditText) findViewById(R.id.idcorreo);

    }

    public void ingresa1(View view) {

        Intent i=new Intent(Inicio.this, MainActivity.class);
        startActivity(i);
    }


}