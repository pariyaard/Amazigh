package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3, button4;
    TextView tvOutput;
    ImageView ivData;
    Spinner spWoorden;

    Integer aantal_woorden;
    String info;
    ArrayAdapter<String> spAdapter;
    List<String> spDataArray = new ArrayList<>();


    // Declaratie array voor Spinnerdata
    ///List<String> spDataArray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        tvOutput= findViewById(R.id.textView);
        ivData= findViewById(R.id.imgData);
        spWoorden= findViewById(R.id.spWoorden);
        button1.setText("DO HELLO");
        button2.setText("SHOW HELLO");
        button3.setText("LOAD LIST");
        button4.setText("MAX");

        spDataArray.add("");
        aantal_woorden=0;
        spAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spDataArray);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> lvAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, spDataArray);
        spWoorden.setAdapter(spAdapter);

        spWoorden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ///get_plaatje(spWoorden.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:

                make_hello_world();
                break;
            case R.id.btn2:
                show_hello_world();
                break;
            case R.id.btn3:
                get_woorden();
                break;
            case R.id.btn4:

                break;
        }
    }
    private void show_hello_world() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://testfirebase-87492-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("message");
        ValueEventListener mListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String m = dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(), "Opgeslagen: "+m,
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        myRef.addValueEventListener(mListener);
    }
    private void make_hello_world(){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://testfirebase-87492-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("message");
        Date currentTime = Calendar.getInstance().getTime();
        String m = "Hallo Wereld!...de tijd is:"+currentTime.toString();
        myRef.setValue(m);
        Toast.makeText(getApplicationContext(), "Melding: "+m, Toast.LENGTH_SHORT);
    }

    private void get_woorden() {
        // Gebruiken met een app die bevat:
        // - TextView tvOutput (ook in layout)
        // - Spinner spWoorden (ook in Layout)
        // - ArrayList spDataArray
        // - Zie de code van je spinner app.

        // referentie naar database, woorden
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://testfirebase-87492-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("woorden");

        // maken van valueeventlistener, voor sync data naar app
        ValueEventListener wListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // leegmaken dataarray
                spDataArray.clear();
                info = "";
                // ophalen van alle data onder 'woorden'
                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    // ophalen woord & vertaling voor toevoegen aan textview
                    info = info+child.child("woordned").getValue().toString();
                    info = info+" ->";
                    info = info+child.child("woordamz").getValue().toString();
                    info = info+"\n";

                    // naam plaatje ophalen en in lijst zetten
                    // spDataArray moet je zelf defenieren
                    spDataArray.add(child.child("imagepath").getValue().toString());
                }
                // tvOutput moet je zelf defenieren
                tvOutput.setText(info);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        myRef.addValueEventListener(wListener);

        Toast.makeText(getApplicationContext(), "Er zijn 6 woorden geladen.", Toast.LENGTH_SHORT).show();


    }
}
