package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String write;
    private int temp = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_tip = findViewById(R.id.tips);
        Button btn_login = findViewById(R.id.login);
        Button btn_show = findViewById(R.id.show);

        btn_tip.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_show.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tips:
                showTips();
                break;
            case R.id.login:
                showLogin();
//                showWrite(true);
                break;
            case R.id.show:
                showWrite();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    write = data.getStringExtra("write");
                    temp = Integer.valueOf(data.getStringExtra("temp"));
                    Log.d("MainActivity", "onActivityResult: returnData is "
                            + write + " temp is " + temp);
                }
        }
    }

    //tips
    private void showTips(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Tips");
        alertDialog.setMessage("Please input you Account to login, " +
                "then write something to come back;");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialog dialog = alertDialog.create();
        dialog.show();
    }

    //login
    private void showLogin(){
        final AlertDialog.Builder dia_login = new AlertDialog.Builder(MainActivity.this);
        dia_login.setCancelable(false);
        dia_login.setTitle("Login");
        final LayoutInflater inflater = LayoutInflater.from(this);
        final View layout = inflater.inflate(R.layout.login_layout, null);
        dia_login.setView(layout);
//        dia_login.setView(R.layout.login_layout);
        dia_login.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                TextView user = layout.findViewById(R.id.userName);
                TextView pwd = layout.findViewById(R.id.passWord);
                Log.d("temp = ", Integer.toString(temp));
                if(user.getText().toString().equals("username") &&
                    pwd.getText().toString().equals("password")){
                    Toast.makeText(MainActivity.this, "Success Login",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("com.example.login.ACTION_START");
                    intent.putExtra("name", "username");
                    intent.putExtra("temp", "1");
                    startActivityForResult(intent, 1);
                }else{
                    Toast.makeText(MainActivity.this, "Please check your account ro " +
                            "password", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dia_login.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = dia_login.create();
        dialog.show();
    }

    //show Write
    private void showWrite(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Show");

        if (temp == 1) {
            alertDialog.setMessage("There is nothing");
        }else{
            alertDialog.setMessage(write);
        }
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialog dialog = alertDialog.create();
        dialog.show();
    }

}
