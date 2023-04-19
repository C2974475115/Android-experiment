package com.example.interface_components;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.view.ActionMode;



import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActionModeActivity extends AppCompatActivity {
    private  ListView listView;
    private List<Item> list;
    private BaseAdapter adapter;
    private String[] name={"One","Two","Three","Four","Five","Six"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);

        listView=findViewById(R.id.list_view);
        list=new ArrayList<Item>();
//        定义Item并加入到list
        for (int i=0;i<6;i++){
            list.add(new Item(name[i],false));
        }
//        对listView进行适配器适配
        adapter=new AdapterCur(list,ActionModeActivity.this);
        listView.setAdapter(adapter);

//        设置listView允许多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
//            选中的数量
            int num=0;
//参数ActionMode长按后出现的标题栏,position当前选中的item序号,id是当前选中的item的id,checked如果是选中事件则为true,取消事件则为false
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
//                调整选定条目
                if(checked==true){
                    list.get(position).setBo(true);
//                    实时刷新
                    adapter.notifyDataSetChanged();
                    num++;
                }else {
                    list.get(position).setBo(false);
//                    实时刷新
                    adapter.notifyDataSetChanged();
                    num--;
                }
//                用Textview显示
                actionMode.setTitle(" "+num+"   "+"Selected");
            }

//       ActionMode 长按出现的标题栏,Menu标题栏的菜单内容


            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//                设置长安后所要显示的标题栏的内容
                MenuInflater inflater=actionMode.getMenuInflater();
                inflater.inflate(R.menu.actionmode_show,menu);
                num=0;
                adapter.notifyDataSetChanged();
                return true;
            }

            /*
             * 可在此方法中进行标题栏UI的创建和更新
             */
            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                adapter.notifyDataSetChanged();
                return false;
            }

            public void refresh(){
                for (int i=0;i<6;i++){
                    list.get(i).setBo(false);
                }
            }

            /*
             * 可在此方法中监听标题栏Menu的监听，从而进行相应操作
             * 设置actionMode菜单每个按钮的点击事件
             */
            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
               switch (menuItem.getItemId()){
//                       删除
                   case R.id.menu_delete:
                       num=0;
                       refresh();
                       adapter.notifyDataSetChanged();
//                       菜单按钮都设置返l
                       actionMode.finish();
                       return true;
                   default:
                       refresh();
                       adapter.notifyDataSetChanged();
                       num=0;
                       return false;

               }

            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                refresh();
                adapter.notifyDataSetChanged();
            }
        });

    }
}