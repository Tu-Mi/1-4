package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.BitSet;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.text_welcome);
        editText = findViewById(R.id.edit_write);

        final Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        temp = Integer.valueOf(intent.getStringExtra("temp"));
        temp = 0;
        textView.setText("Welcome " + name);

        String hello = Read();
        if(!TextUtils.isEmpty(hello)){
            editText.setText(hello);
            editText.setSelection(hello.length());
        }

        Button but_ok = findViewById(R.id.ok);
        Button btn_back = findViewById(R.id.btn_back);

        but_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(SecondActivity.this, "已保存 ", Toast.LENGTH_SHORT).show();

            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String writing = editText.getText().toString();

                if (!writing.isEmpty()){
                    Intent intent1 = new Intent();
                    intent1.putExtra("write", writing);
                    intent1.putExtra("temp", Integer.toString(temp));
                    setResult(RESULT_OK, intent1);
                    Saved();
                    finish();
                }else{
                    Toast.makeText(SecondActivity.this, "Please write something to " +
                            "come back", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //saved
    private void Saved(){
        String data = editText.getText().toString();
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("hello_world",
                    Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                try{
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //read
    private String Read(){
        FileInputStream input = null;
        BufferedReader reader = null;
        StringBuilder context = new StringBuilder();
        try{
            input = openFileInput("hello_world");
            reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while ((line = reader.readLine()) != null){
                context.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return context.toString();
    }
}
