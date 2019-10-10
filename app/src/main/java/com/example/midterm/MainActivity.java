package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button2);
        EditText editText=findViewById(R.id.editText2);
        TextView textView=findViewById(R.id.textView);

        button.setOnClickListener((View) -> {
            String input = editText.getText().toString();

            OkHttpClient client = new OkHttpClient();
            String url;
            url = "https://learn.operatoroverload.com/rental/"+input;
            Request request = new Request.Builder().url(url).build();

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        Response response = client.newCall(request).execute();
                        String text = response.body().string();
                        Log.d("response", text );

                        runOnUiThread(()->{
                            textView.setText(text);
                        });
                    } catch (IOException e) {
                        runOnUiThread(()->{
                            textView.setText("Error");
                        });

                    }
                }
            };

            thread.start();

        });

    }
}
