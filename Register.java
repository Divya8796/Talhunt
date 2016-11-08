package com.example.divya.talenthunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class Register extends AppCompatActivity {

    private ProgressDialog pDialog;
    JSONParser jsonParser =new JSONParser();
    EditText inputemail,inputname,inputpassword;
    Button btn_register;
    private static String url_create_user="http://192.168.43.213/Talhunt/insert.php";//What to give here?
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputemail=(EditText)findViewById(R.id.input_email1);
        inputname=(EditText)findViewById(R.id.input_name1);
        inputpassword=(EditText)findViewById(R.id.input_password1);
        btn_register=(Button)findViewById(R.id.btn_register);

        btn_register.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    s=new CreateUser().execute().get();
                    if(s.equals("Success"))
                    {
                        Intent i=new Intent(getApplicationContext(),upload.class);
                        startActivity(i);
                    }
                     else if(s.equals("fail"))
                    {
                        Toast.makeText(getApplicationContext(), "Something is missing..", Toast.LENGTH_LONG).show();
                    }
            } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
    });

}


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    class  CreateUser extends AsyncTask<String,String,String>
    {
        SessionaManagement session;
        int x;
        String status;

        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog=new ProgressDialog(Register.this);
            pDialog.setMessage("Wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String email=inputemail.getText().toString();
            String name=inputname.getText().toString();
            String password=inputpassword.getText().toString();

            HashMap<String,String> p= new HashMap<String,String>();
            p.put("email",email);
            p.put("name", name);
            p.put("password", password);

            JSONObject json=jsonParser.makeHttpRequest(url_create_user,"POST",p);
            Log.d("Create Response", json.toString());
            try {
                x=json.getInt("x");
                session=new SessionaManagement(getApplicationContext());
                if (x==1)
                {
                    status="Success";
                    session.createLoginSession(email, password);
                }
                else
                {
                    status="fail";
                }
            }
            catch (JSONException e)
            {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }

            return status;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
        }
    }
}