package com.example.guillermorios.ingresoautos;

import com.google.firebase.database.FirebaseDatabase;

public class Persistens extends android.app.Application {

    public void onCreate(){

        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
