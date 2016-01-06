package com.example.test.jsonparser;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
Button b1;
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.bthhit);
        t1=(TextView)findViewById(R.id.tvJSONitem);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
            }
        });

    }
    public class JSONTask extends AsyncTask<String,String,String>{
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        @Override
        protected String doInBackground(String... params) {

            try {
                URL url=new URL(params[0]);
                connection= (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream= connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                String line;
                StringBuffer buffer= new StringBuffer();
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                return buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null) {
                    connection.disconnect();
                }
                try {
                    if(reader!=null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            t1.setText(result);

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
