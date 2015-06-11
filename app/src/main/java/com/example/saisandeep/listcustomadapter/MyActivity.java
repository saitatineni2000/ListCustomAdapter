package com.example.saisandeep.listcustomadapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MyActivity extends Activity {

    ListView list1;
    String[] names;
    String[] desc;
    int[] images = {R.drawable.about_top_bg, R.drawable.app_icon, R.drawable.bg_gettingstarted, R.drawable.about_top_bg, R.drawable.app_icon};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //list1= (ListView) findViewById(R.id.listView1);
        Resources res = getResources();
        names = res.getStringArray(R.array.names);
        desc = res.getStringArray(R.array.description);
        list1 = (ListView) findViewById(R.id.listView1);
        NameAdapter single = new NameAdapter(this, names, images, desc);
        list1.setAdapter(single);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class NameAdapter extends ArrayAdapter<String> {
        Context contex;
        int[] images;
        String[] nametitles;
        String[] desctitles;

        public NameAdapter(Context context, String[] titles, int[] imgs, String[] desctit) {
            super(context, R.layout.single_row, R.id.textView, names);
            this.contex = context;
            this.nametitles = titles;
            this.images = imgs;
            this.desctitles = desctit;
        }

        class MyViewHolder {
            ImageView image;
            TextView name, desc;

            MyViewHolder(View v) {
                image = (ImageView) v.findViewById(R.id.imageView);
                name = (TextView) v.findViewById(R.id.textView);
                desc = (TextView) v.findViewById(R.id.textView2);
            }
        }

        @Override
        //get view is called at runtime whenever a row is created and displayed to the user
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            MyViewHolder holder;
            //optimization by 150%
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row, parent, false);
                holder = new MyViewHolder(row);
                row.setTag(holder);

            }
            //recycling
            else {
                holder = (MyViewHolder) row.getTag();
            }
            // ImageView image= (ImageView) row.findViewById(R.id.imageView);
            //TextView name= (TextView) row.findViewById(R.id.textView);
            //TextView desc= (TextView) row.findViewById(R.id.textView2);

            holder.image.setImageResource(images[position]);
            holder.name.setText(nametitles[position]);
            holder.desc.setText(desctitles[position]);

            return row;
        }
    }
}

