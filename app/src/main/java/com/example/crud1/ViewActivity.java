package com.example.crud1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ArrayList<String> al_id = new ArrayList<String>();
    ArrayList<String> al_name = new ArrayList<String>();
    ArrayList<String> al_username = new ArrayList<String>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lv = findViewById(R.id.list_users);
        display();
    }

    public void display() {
        DBHelper helper = new DBHelper(this);
        Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM " +helper.table, null);
        al_id.clear();al_name.clear();al_username.clear();
        while (cursor.moveToNext()) {
            al_id.add(cursor.getString(0));
            al_name.add(cursor.getString(1));
            al_username.add(cursor.getString(2));
        }

        UsersAdapter adapter = new UsersAdapter(al_id,al_name,al_username,this);
        lv.setAdapter(adapter);

//        popup

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                DBHelper dbHelper = new DBHelper(getApplicationContext());
//                int id1 = Integer.parseInt(al_id.get(position));
//                String message = "";
//                if (dbHelper.deleteData(id1)) {
//                    message = "One record is deleted";
//                }
//                else {
//                    message = "There is something wrong";
//                }
//
////                Toast.makeText(ViewActivity.this, ""+al_id.get(position), Toast.LENGTH_SHORT).show();
//                Toast.makeText(ViewActivity.this, ""+message, Toast.LENGTH_SHORT).show();

                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),lv);
                popupMenu.getMenuInflater().inflate(R.menu.action_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String message = "";
                        int id1 = Integer.parseInt(al_id.get(position));
                        SharedPreferences sharedPreferences = getSharedPreferences("modify_delete", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("id", id1);
                        editor.putString("name", al_name.get(position));
                        editor.putString("email", al_username.get(position));
                        editor.commit();
                        switch (item.getItemId()) {
                            case R.id.modify:
//                                message = "Modify item";
                                startActivity(new Intent(getApplicationContext(),ModifyActivity.class));
                                break;
                            case R.id.delete:
//                                message = "Delete record";
                                message = delete(id1);
                                startActivity(new Intent(getApplicationContext(),ViewActivity.class));
                                Toast.makeText(ViewActivity.this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();
                                break;
                        }

//                        Toast.makeText(ViewActivity.this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    public String delete(int del_id){
        DBHelper dbHelper = new DBHelper(this);
        String message = "";
        if (dbHelper.deleteData(del_id)) {
            return  "The record is deleted";
        }
        else {
            return  "There is something wrong";
        }

    }
}