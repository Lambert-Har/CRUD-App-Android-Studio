/**
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
 *
 * @author  Lambert Har
 * @version 1.0
 * @since   2023-06-02
 */

package com.example.crud1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText e_name,e_email,e_password;
    Button b_add,b_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e_name = findViewById(R.id.name);
        e_email = findViewById(R.id.email);
        e_password = findViewById(R.id.password);
        b_add = findViewById(R.id.add);
        b_view = findViewById(R.id.view);

        b_add.setOnClickListener(this);
        b_view.setOnClickListener(this);

    }

    public void insertCallBack() {
        DBHelper obj = new DBHelper(getApplicationContext());
        String name,email,pass;
        name = e_name.getText().toString();
        email = e_email.getText().toString();
        pass = e_password.getText().toString();

        String message ="";
        if(name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            message = "Some field is empty";
        }
        else {
            if (obj.insertData(name,email,pass)) {
                message = "Data well recorded";
                clearMethod();
            }
            else {
                message = "Data not inserted";
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
    String message="";
    switch (view.getId()){
        case R.id.add:
//            message = "Add button is clicked";
            insertCallBack();
            break;
        case R.id.view:
//            message = "View button is clicked";
            startActivity(new Intent(getApplicationContext(),ViewActivity.class));
            break;
    }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void clearMethod() {
        e_name.setText("");
        e_email.setText("");
        e_password.setText("");
    }

//    public void insertCallback(View view){
//        DBHelper obj = new DBHelper(getApplicationContext());
//        if (obj.insertData("Amina","Amina@gmail.com","123")){
//            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//        }
//    }
}