package com.example.phone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class HomeActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> phoneNumber = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    static final int request1 = 101;
    String number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        names.add("Jack");
        names.add("Jam");
        names.add("John");
        names.add("Sam");
        phoneNumber.add("123456");
        phoneNumber.add("223890");
        phoneNumber.add("326786");
        phoneNumber.add("423689");

        listView = findViewById(R.id.ListView);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        Intent i = new Intent(HomeActivity.this, AddContactActivity.class);
        startActivityForResult(i,request1);
        Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
        return true;


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

      String slectedNumber = phoneNumber.get(info.position);

        if (item.getTitle().equals("Call")) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);

            callIntent.setData(Uri.parse("tel:" + slectedNumber));

            if (ActivityCompat.checkSelfPermission(HomeActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }

            startActivity(callIntent);
        }


      else  if (item.getTitle().equals("Delete")) {

        names.remove(info.position);
        }


        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request1 && resultCode == RESULT_OK && data != null) {

            String name = data.getStringExtra("name");
            //Toast.makeText(HomeActivity.this, name + "", Toast.LENGTH_SHORT).show();
            String phone = data.getStringExtra("phone");
            names.add(name);
            phoneNumber.add(phone);
            adapter.notifyDataSetChanged();


        }


    }
}



