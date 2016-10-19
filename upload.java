package com.example.divya.talenthunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.divya.talenthunt.Upload_Handle;
import java.net.URI;


public class upload extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChoose;
    private Button buttonUpload;
    private TextView textView;
    private TextView textViewResponse;

    private static final int SELECT_VIDEO=3;

    private String selectedPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        buttonChoose=(Button)findViewById(R.id.buttonchoose);
        buttonUpload=(Button)findViewById(R.id.buttonupload);

        textView=(TextView)findViewById(R.id.textView);
        textViewResponse=(TextView)findViewById(R.id.textViewresponse);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    public void chooseVideo()
    {
        Intent i=new Intent();
        i.setType("video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select a Video"), SELECT_VIDEO);
    }

    public void onActivityResult(int requestcode,int resultcode,Intent data) {
        if (resultcode == RESULT_OK) {
            if (requestcode == SELECT_VIDEO) {
                System.out.println("Select_Video");
                Uri selectedImageUri = data.getData();
                selectedPath = getPath(selectedImageUri);
                textView.setText(selectedPath);
            }
        }
    }

    private String getPath(Uri uri)
    {
        Cursor cursor=getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id=cursor.getString(0);
        document_id=document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor=getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID+"=?",new String[]{document_id},null);
        cursor.moveToFirst();
        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }

    private void uploadVideo()
    {
        class UploadVideo extends AsyncTask<Void,Void,String>
        {
            ProgressDialog uploading;

            protected void onPreExecute()
            {
                super.onPreExecute();
                uploading=ProgressDialog.show(upload.this,"Uploading File","please wait",false,false);
            }

            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                uploading.dismiss();
                textViewResponse.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
                textViewResponse.setMovementMethod(LinkMovementMethod.getInstance());
            }

            protected String doInBackground(Void... params)
            {
                Upload_Handle u=new Upload_Handle();
                String msg=u.uploadVideo(selectedPath);
                return msg;
            }
        }
        UploadVideo uv=new UploadVideo();
        uv.execute();
    }

    public void onClick(View v)
    {
        if(v==buttonChoose)
        {
            chooseVideo();
        }
        if(v==buttonUpload)
        {
            uploadVideo();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upload, menu);
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
