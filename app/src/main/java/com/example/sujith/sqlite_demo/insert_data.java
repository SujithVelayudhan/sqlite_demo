package com.example.sujith.sqlite_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert_data extends AppCompatActivity {

    EditText insert_namep,insert_numbp;
    Button insert_buttp;

    DBhelper dhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        dhelp=new DBhelper(this);

        insert_namep=findViewById(R.id.insert_name);
        insert_numbp=findViewById(R.id.insert_num);
        insert_buttp=findViewById(R.id.insert_submit);

        insert_buttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(dhelp.insertdata(insert_namep.getText().toString(),insert_numbp.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(getApplicationContext(),page1.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(insert_data.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
