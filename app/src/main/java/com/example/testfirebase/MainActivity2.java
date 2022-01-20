package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    Context x;
    ItemAdapter adapter;
    //WoordAdapter adapter;
    DatabaseReference algemeen, woorden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // reference naar de data
        woorden = FirebaseDatabase.getInstance().getReference("woorden").getRef();
        x = getApplicationContext();
// klaarzetten van de recyclerview
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // bouwen van de dataquery
        FirebaseRecyclerOptions<Woord> options
                = new FirebaseRecyclerOptions.Builder<Woord>()
                .setQuery(woorden, Woord.class)
                .build();
// bouwen van de adapter met query
        adapter = new ItemAdapter(options , x);
// koppelen van de adapter
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}