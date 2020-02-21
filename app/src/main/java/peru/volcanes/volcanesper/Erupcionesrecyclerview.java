//package com.mytrendin.firebaserecyclerviewcardview;
package peru.volcanes.volcanesper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import peru.volcanes.volcanesper.m_model.erupciones;

import peru.volcanes.volcanesper.m_ui.RecyclerAdapter;


public class Erupcionesrecyclerview extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<erupciones> list;
    RecyclerView recycle;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciclerlayout);
        view = (Button) findViewById(R.id.view);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
       // myRef = database.getReference("message");


        myRef = database.getReference("actividadvolcanica").child("volcanes").child("1493157381161").child("erupciones");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<erupciones>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    erupciones value = dataSnapshot1.getValue(erupciones.class);
                    erupciones fire = new erupciones();
                    String name = value.getFuente();
                    String address = value.getObservaciones();
                    String email = value.getYear();
                    fire.setFuente(name);
                    fire.setObservaciones(email);
                    fire.setYear(address);
                    list.add(fire);


                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, Erupcionesrecyclerview.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Erupcionesrecyclerview.this, 1);
                    /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                    // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                    recycle.setLayoutManager(recyce);
                    recycle.setItemAnimator(new DefaultItemAnimator());
                    recycle.setAdapter(recyclerAdapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });




/*
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,Erupcionesrecyclerview.this);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(Erupcionesrecyclerview.this,1);
                /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recycle.setLayoutManager(recyce);
                recycle.setItemAnimator( new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);




            }
        });
*/

    }


    void cargar(View v) {


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, Erupcionesrecyclerview.this);
        RecyclerView.LayoutManager recyce = new GridLayoutManager(Erupcionesrecyclerview.this, 1);
        /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
        // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycle.setLayoutManager(recyce);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

}


/*
package peru.volcanes.volcanesper;

public class Erupcionesrecyclerview {
}
*/