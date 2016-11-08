package com.example.divya.talenthunt;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class Homepage extends TabActivity {

    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        TabHostWindow=(TabHost)findViewById(android.R.id.tabhost);
        TabHost.TabSpec tabmenu1=TabHostWindow.newTabSpec("Login");
        TabHost.TabSpec tabmenu2=TabHostWindow.newTabSpec("Register");
        TabHost.TabSpec tabmenu3=TabHostWindow.newTabSpec("Hirer");

        tabmenu1.setIndicator("Login");
        tabmenu1.setContent(new Intent(this, Login.class));

        tabmenu2.setIndicator("Register");
        tabmenu2.setContent(new Intent(this,Register.class));

        tabmenu3.setIndicator("Hirer");
        tabmenu3.setContent(new Intent(this,Hirer.class));

        TabHostWindow.addTab(tabmenu1);
        TabHostWindow.addTab(tabmenu2);
        TabHostWindow.addTab(tabmenu3);
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
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
    }*/
}
