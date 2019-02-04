package com.example.sujith.sqlite_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_data extends AppCompatActivity {

    EditText update_namep,update_nump;
    Button update_submitp;

    String temp;

    DBhelper dhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dhelp=new DBhelper(this);

        update_namep=findViewById(R.id.update_name);
        update_nump=findViewById(R.id.update_num);
        update_submitp=findViewById(R.id.update_submit);

        Bundle obj=getIntent().getExtras();
        update_namep.setText(obj.getString("name"));
        update_nump.setText(obj.getString("num"));

        temp=obj.getString("name");



        update_submitp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(dhelp.updatedata(update_namep.getText().toString(),
                        update_nump.getText().toString(),temp))
                {
                    Toast.makeText(update_data.this, "UPDATED", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),page1.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(update_data.this, "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
