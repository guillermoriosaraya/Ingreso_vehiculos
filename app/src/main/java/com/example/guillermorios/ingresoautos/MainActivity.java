package com.example.guillermorios.ingresoautos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guillermorios.ingresoautos.model.Persona;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {


    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;


    EditText nomP, appP, correoP, marcaP, modelop, patentep;
    ListView listv_P;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomP = findViewById(R.id.txt_nombre);
        appP = findViewById(R.id.txt_apellido);
        correoP = findViewById(R.id.txt_correo);
        marcaP = findViewById(R.id.txt_marca);
        modelop = findViewById(R.id.txt_modelo);
        patentep = findViewById(R.id.txt_patente);

        listv_P = findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listarDatos();

        listv_P.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                appP.setText(personaSelected.getApellido());
                correoP.setText(personaSelected.getCorreo());
                marcaP.setText(personaSelected.getMarca());
                modelop.setText(personaSelected.getModelo());
                patentep.setText(personaSelected.getPatente());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    listv_P.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String nombre = nomP.getText().toString();
        String correo = correoP.getText().toString();
        String marca = marcaP.getText().toString();
        String apellido = appP.getText().toString();
        String modelo = modelop.getText().toString();
        String patente = patentep.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                if(nombre.equals("")|| correo.equals("") || marca.equals("") || apellido.equals("") || modelo.equals("") || patente.equals("")){
                    validacion();
                }
                else {
                    Persona p = new Persona();
                    p.setIDP(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCorreo(correo);
                    p.setMarca(marca);
                    p.setModelo(modelo);
                    p.setPatente(patente);
                    databaseReference.child("usuarios").child(p.getIDP()).setValue(p);
                    Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
                    limpiarCajas();

                }
                break;
            }
            case R.id.icon_save:{
                Persona p = new Persona();
                p.setIDP(personaSelected.getIDP());
                p.setNombre(nomP.getText().toString().trim());
                p.setApellido(appP.getText().toString().trim());
                p.setCorreo(correoP.getText().toString().trim());
                p.setMarca(marcaP.getText().toString().trim());
                p.setModelo(modelop.getText().toString().trim());
                p.setPatente(patentep.getText().toString().trim());
                databaseReference.child("usuarios").child(p.getIDP()).setValue(p);
                Toast.makeText(this, "Guardar", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Persona p = new Persona();
                p.setIDP(personaSelected.getIDP());
                databaseReference.child("usuarios").child(p.getIDP()).removeValue();
                Toast.makeText(this, "Borrar", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            default:break;
        }

        return true;
    }

    private void limpiarCajas() {
        nomP.setText("");
        correoP.setText("");
        appP.setText("");
        marcaP.setText("");
        modelop.setText("");
        patentep.setText("");
    }

    private void validacion() {
        String nombre = nomP.getText().toString();
        String correo = correoP.getText().toString();
        String marca = marcaP.getText().toString();
        String apellido = appP.getText().toString();
        String modelo = modelop.getText().toString();
        String patente = patentep.getText().toString();

        if (nombre.equals("")){
            nomP.setError("Required");
        }
        else if (apellido.equals("")){
            appP.setError("Required");
        }
        else if (correo.equals("")){
            correoP.setError("Required");

        }
        else if (marca.equals("")){
            marcaP.setError("Required");
        }

        else if (modelo.equals("")){
            modelop.setError("Required");
        }

        else if (patente.equals("")){
            patentep.setError("Required");
        }
    }

}
