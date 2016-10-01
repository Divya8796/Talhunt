package com.example.divya.talenthunt;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Upload_Handle
{
    public static final String UPLOAD_URL="http://192.168.43.213/Talhunt/Upload.php";
    private int serverResponseCode;

    public String uploadVideo(String file)
    {
        String filename=file;
        HttpURLConnection con=null;
        DataOutputStream dos=null;
        String lineEnd="\r\n";
        String twoHyphens="--";
        String boundary="*****";
        int bytesRead,bytesAvailable,buffersize;
        byte[] buffer;
        int maxBuffersize= 1* 1024 * 1024;

        File sourceFile=new File(file);
        if(!sourceFile.isFile())
        {
            Log.e("Huzza","Source file not found");
            return null;
        }
        try
        {
            FileInputStream fileInputStream=new FileInputStream(sourceFile);
            URL url=new URL(UPLOAD_URL);
            con=(HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("ENCTYPE", "multipart/form-data");
            con.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
            con.setRequestProperty("myFile",filename);

            dos=new DataOutputStream(con.getOutputStream());
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"myFile\";filename=\""+filename+"\""+lineEnd);
            dos.writeBytes(lineEnd);

            bytesAvailable=fileInputStream.available();
            Log.i("Huzza","Initial.available:"+bytesAvailable);

            buffersize=Math.min(bytesAvailable,maxBuffersize);
            buffer=new byte[buffersize];

            bytesRead=fileInputStream.read(buffer,0,buffersize);

            while (bytesRead>0)
            {
                dos.write(buffer,0,buffersize);
                bytesAvailable=fileInputStream.available();
                buffersize=Math.min(bytesAvailable,maxBuffersize);
                bytesRead=fileInputStream.read(buffer,0,buffersize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens+boundary+twoHyphens+lineEnd);

            serverResponseCode=con.getResponseCode();

            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(serverResponseCode==200)
        {
            StringBuilder sb=new StringBuilder();
            try {
                BufferedReader rd=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line=rd.readLine())!=null)
                {
                    sb.append(line);
                }
                rd.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        else {
            return "Could not Upload";
        }

    }

}
