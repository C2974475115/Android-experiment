package com.example.interface_components;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
//          String[] ctype=new String[]{"全部","图书","游戏","电视"};
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ctype);
//        ListView listView=findViewById(R.id.listview);
//        listView.setAdapter(adapter);
        int []imageid=new int[]{R.drawable.cat,R.drawable.dog,R.drawable.elephant,
                R.drawable.lion,R.drawable.monkey,R.drawable.tiger};
        String[]title=new String[]{"cat","dog","elephant","lion","monkey","tiger"};
        List<Map<String,Object>> listitem=new ArrayList<Map<String,Object>>();
        for(int i=0;i<imageid.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("image",imageid[i]);
            map.put("name",title[i]);
            listitem.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,listitem,R.layout.listview_main,new String[]{"name","image"},
                new int[]{R.id.title,R.id.image});
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String,Object> map=(Map<String,Object>)adapterView.getItemAtPosition(i);

                Toast.makeText(ListviewActivity.this,map.get("name").toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}