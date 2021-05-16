package com.mukuljoshi.adduserinsqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText et_name, et_email;
    private Button addBt, viewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        et_name = findViewById(R.id.edit_name);
        et_email = findViewById(R.id.edit_email);
        addBt = findViewById(R.id.add_btn);
        viewBtn = findViewById(R.id.view_data);

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();

                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Required all filed!", Toast.LENGTH_SHORT).show();
                    //et_name.requestFocus();

                } else {
                    DatabaseHelperClass helperClass = new DatabaseHelperClass(MainActivity.this);
                    EmployeeModelClass employeeModelClass = new EmployeeModelClass(name, email);
                    helperClass.addEmployee(employeeModelClass);
                    Toast.makeText(MainActivity.this, "Data add successfully ",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ViewEmployee.class));
                }

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewEmployee.class);
                startActivity(intent);
            }
        });
    }
}