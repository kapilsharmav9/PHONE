package com.example.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
Button btnsave;
EditText ename,ephone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ename=findViewById(R.id.entername);
        ephone=findViewById(R.id.enterphone);
        btnsave=findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ename.getText().toString();
                String phone=ephone.getText().toString();
                Intent i=new Intent();
                i.putExtra("name",name);
                i.putExtra("phone",phone);
                setResult(RESULT_OK,i);
                finish();
               // Toast.makeText(getApplicationContext(),""+name+phone,Toast.LENGTH_LONG).show();
            }
        });
    }
}
