package com.example.sujith.sqlite_demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class page1 extends AppCompatActivity {

    ListView list_viewp;

    TextView namep,numberp;

    ArrayList<String> aname;
    ArrayList<String> anum;

    DBhelper dhelp;

    LAdapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        dhelp=new DBhelper(this);

        list_viewp=findViewById(R.id.list_view);

        try {


            aname = new ArrayList<>();
            aname = dhelp.getAllNames();

            anum = new ArrayList<>();
            anum = dhelp.getAllNumber();

            adapt = new LAdapter();

            list_viewp.setAdapter(adapt);

            list_viewp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent i=new Intent(getApplicationContext(),view_details.class);
                    i.putExtra("k1",aname.get(position));
                    startActivity(i);


                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(this, "List Empty", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opt,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();

        if (id==R.id.insert_data)
        {
            Intent i=new Intent(getApplicationContext(),insert_data.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    class LAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return aname.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater in=(LayoutInflater)getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=in.inflate(R.layout.inf_list,null);
            namep=convertView.findViewById(R.id.list_name);
            numberp=convertView.findViewById(R.id.list_num);

            namep.setText(aname.get(position));
            numberp.setText(anum.get(position));

            return convertView;
        }
    }
}
