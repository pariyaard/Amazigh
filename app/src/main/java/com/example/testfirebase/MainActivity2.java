package com.example.testfirebase;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 {
    private RecyclerView recyclerView;
    DatabaseReference algemeen, woorden;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



            // reference naar de data
            woorden = FirebaseDatabase.getInstance().getReference("woorden").getRef();
// klaarzetten van de recyclerview
    recyclerView = recyclerView.findViewById();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
}
}
