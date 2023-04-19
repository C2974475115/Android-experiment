package com.example.interface_components;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txt = findViewById(R.id.txt);
        // 为文本框注册上下文菜单
        registerForContextMenu(txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        填装R.menu.menu_main对应的菜单，并添加到menu
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

////    创建上下文菜单是触发该方法
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        //        填装R.menu.context对应的菜单，并添加到menu中
//        getMenuInflater().inflate(R.menu.context,menu);
//        menu.setHeaderTitle("请选择背景颜色");
//    }

//    菜单被单击后的回调方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.isCheckable()){
            item.setChecked(true);
        }
        // 判断单击的是哪个菜单项，并有针对性地做出响应
        switch (item.getItemId()){
            case R.id.font_小:txt.setTextSize(10*2);break;
            case R.id.font_中:txt.setTextSize(16*2);break;
            case R.id.font_大:txt.setTextSize(20*2);break;
            case R.id.red_font:txt.setTextColor(Color.RED);break;
            case R.id.black_font:txt.setTextColor(Color.BLACK);break;
            case R.id.plain_item:
                Toast.makeText(MenuActivity.this,"您单击了普通菜单项",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}