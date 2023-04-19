package com.example.interface_components;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);

//      创建监听器
        bt1.setOnClickListener(listner);
        bt2.setOnClickListener(listner2);
        bt3.setOnClickListener(listner3);
        bt4.setOnClickListener(listner4);

    }
    public  void showDiglog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.alert_dialog);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    Button.OnClickListener listner = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ListviewActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener listner2 = new Button.OnClickListener() {
        public void onClick(View v) {
            showDiglog();
        }
    };
    Button.OnClickListener listner3=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent(MainActivity.this,MenuActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener listner4=new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ActionModeActivity.class) ;
            startActivity(intent);
        }
    };

}


