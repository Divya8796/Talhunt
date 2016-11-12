package com.example.divya.talenthunt;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;


public class new_tab extends AppCompatActivity {
    public TabHost mytabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tab);
        // Recuperation du TabHost
        mytabhost =(TabHost) findViewById(android.R.id.tabhost);

       TabHost.TabSpec tabmenu1 = mytabhost.newTabSpec("First tab");
        TabHost.TabSpec tabmenu2 = mytabhost.newTabSpec("Second tab");
        TabHost.TabSpec tabmenu3 = mytabhost.newTabSpec("Third tab");

        tabmenu1.setIndicator("Timeline", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme()));
        tabmenu1.setContent(new Intent(this, upload.class));

        tabmenu2.setIndicator("Search");
        tabmenu2.setContent(new Intent(this, searchable.class));

        tabmenu3.setIndicator("Profile");
        tabmenu3.setContent(new Intent(this, Profile.class));

        mytabhost.addTab(tabmenu1);
        mytabhost.addTab(tabmenu2);
        mytabhost.addTab(tabmenu3);
/*        TabHost.TabSpec spec = mytabhost.newTabSpec("tab_creation");
        // text and image of tab
        spec.setIndicator("Create adresse", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme()));
        // specify layout of tab
        spec.setContent(R.id.onglet1);
        // adding tab in TabHost
        mytabhost.addTab(spec);

// otherwise :
        mytabhost.addTab(mytabhost.newTabSpec("tab_inser").setIndicator("Delete", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme())));

        mytabhost.addTab(mytabhost.newTabSpec("tab_affiche").setIndicator("Show All", getResources().getDrawable(android.R.drawable.ic_menu_add, getApplicationContext().getTheme())));

        ListView lv=(ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.activity_login);
        lv.setAdapter(adapter);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
