package com.example.divya.talenthunt;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.HashMap;


public class Login extends ActionBarActivity {
    private ProgressDialog pDialog;

    JSONParser jsonParser =new JSONParser();
    EditText inputemail;
    EditText inputpassword;
    Button btn_login;

    private static String url_create_user="http://192.168.43.213/Talhunt/login.php";//What to give here?
    private static final String TAG_SUCCESS="success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputemail = (EditText) findViewById(R.id.input_email);
        inputpassword = (EditText) findViewById(R.id.input_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateUser().execute();
            }
        });
    }
        class  CreateUser extends AsyncTask<String,String,String>
        {
            protected void onPreExecute()
            {
                super.onPreExecute();
                pDialog=new ProgressDialog(Login.this);
                pDialog.setMessage("Wait");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {

                String email=inputemail.getText().toString();
                String password=inputpassword.getText().toString();

                HashMap<String,String> p= new HashMap<String,String>();
                p.put("email",email);
                p.put("password", password);

                JSONObject json=jsonParser.makeHttpRequest(url_create_user,"POST",p);
                Log.d("Create Response", json.toString());
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pDialog.dismiss();
            }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
