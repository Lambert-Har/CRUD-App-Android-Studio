package com.example.crud1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {
    EditText mod_name,mod_email;
    Button b_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        SharedPreferences sharedPreferences = getSharedPreferences("modify_delete", Context.MODE_PRIVATE);
        String e_name = sharedPreferences.getString("name", "default");
        String e_email = sharedPreferences.getString("email", "default");
        int e_id = sharedPreferences.getInt("id",0);

        mod_name = findViewById(R.id.mod_name);
        mod_email = findViewById(R.id.mod_email);
        b_apply = findViewById(R.id.btn_apply);

        mod_name.setText(e_name);
        mod_email.setText(e_email);
        b_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_name = mod_name.getText().toString();
                String new_username = mod_email.getText().toString();
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                if (dbHelper.modifyData(e_id, new_name,new_username)) {
                    startActivity(new Intent(getApplicationContext(),ViewActivity.class));
                } else {
                    Toast.makeText(ModifyActivity.this, "There is something wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}