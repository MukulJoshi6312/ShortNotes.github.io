package com.mukuljoshi.adduserinsqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class ViewEmployee extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView image;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);
        //  getSupportActionBar().hide();
        getSupportActionBar().setTitle("My Notes");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.floatingActionButton);

        image = findViewById(R.id.bg_image);
        textView = findViewById(R.id.datatext);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewEmployee.this, MainActivity.class);
                startActivity(intent);
            }
        });


        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<EmployeeModelClass> employeeModelClasses = databaseHelperClass.getEmployeeList();
        Collections.reverse(employeeModelClasses);

        if (employeeModelClasses.size() > 0) {
            EmployeeAdapterClass adapterClass = new EmployeeAdapterClass(employeeModelClasses, this);
            recyclerView.setAdapter(adapterClass);
            image.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "There is no Data", Toast.LENGTH_LONG).show();
            image.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
    }
}

