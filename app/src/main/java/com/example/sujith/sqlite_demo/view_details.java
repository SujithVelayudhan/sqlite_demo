package com.example.sujith.sqlite_demo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class view_details extends AppCompatActivity {

    DBhelper dhelp;

    TextView view_namep,view_numberp;

    String value,na,nu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        view_namep=findViewById(R.id.view_name);
        view_numberp=findViewById(R.id.view_number);

        dhelp=new DBhelper(this);

        Bundle obj=getIntent().getExtras();
        value=obj.getString("k1");
        if (obj!=null)
        {

            if (value!=null)
            {
                Cursor c=dhelp.getData(value);

                c.moveToFirst();
                na=c.getString(c.getColumnIndex(dhelp.NAME));
                nu=c.getString(c.getColumnIndex(dhelp.NUMBER));

                view_namep.setText("Name : "+na);
                view_numberp.setText("Number : "+nu);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.view_opt,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();

        if (id==R.id.delete_data)
        {
            dhelp.deleteContact(value);
            Intent i=new Intent(getApplicationContext(),page1.class);
            startActivity(i);
        }

        if (id==R.id.update_data)
        {
            Intent i=new Intent(getApplicationContext(),update_data.class);
            i.putExtra("name",na);
            i.putExtra("num",nu);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }
}
