package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView lastnameF;
    String answerHTTP;
    String lastnameS, firstnameS;
    private String SERV_URL = "http://192.168.1.67:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lastnameF = (TextView) findViewById(R.id.lastnameF);
    }

    public void sendPOST(View view) {
        EditText lastname = (EditText) findViewById(R.id.lastname);
        EditText firstname = (EditText) findViewById(R.id.firstname);
        lastnameS = lastname.getText().toString();
        firstnameS = firstname.getText().toString();
        new MyAsyncTask().execute("");
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // Создаем HttpClient и Post Header
            /*HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.72.3:8080");*/

            RequestBody requsestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("lastname", lastnameS)
                    .addFormDataPart("firstname", firstnameS)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(SERV_URL)
                    .post(requsestBody)
                    .build();
            Response response =null;

            try{
                response =client.newCall(request).execute();

            }
            catch (IOException e){
                e.printStackTrace();
              //  Toast.makeText(getApplicationContext(),"Проблемы с запросом", Toast.LENGTH_LONG);
            }

            if(response !=null && response.code() == 200){
                try {
                    answerHTTP = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("lastname", lastnameS));
                nameValuePairs.add(new BasicNameValuePair("firstname", firstnameS));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                // Выполняем HTTP Post запрос
                HttpResponse response = httpclient.execute(httppost);
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    answerHTTP = EntityUtils.toString(entity);
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            return null;*/
        return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            lastnameF.setText(answerHTTP);
        }
    }
}
