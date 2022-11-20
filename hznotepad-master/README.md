# notepad实现
notepad期中实验

一、内容简介

1、基本要求：

1）显示条目增加时间戳显示

2）添加笔记查询功能

2、附加功能

1）背景美化

更改了背景图片，美化了记事本

2）字体大小更换与颜色更换

可以改变字体的大小，有小、中、大三种大小并且可以在黑红两色中更换字体的颜色

3）排序功能

可以按照创造时间顺序或者最后修改时间顺序将笔记排序

二、关键步骤代码及截图

1、NoteList中显示条目增加时间戳显示
1)添加显示时间的TextView在layout的布局文件notelist_item.xml中。
```
<TextView
    android:id="@+id/text1"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:textAppearance="?android:attr/textAppearanceLarge"
    android:gravity="center_vertical"
    android:paddingLeft="10dip"
    android:singleLine="true"
    android:layout_weight="1"
    android:layout_margin="0dp"
    />

<TextView
    android:id="@+id/text2"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:textAppearance="?android:attr/textAppearanceLarge"
    android:textSize="12dp"
    android:gravity="center_vertical"
    android:paddingLeft="10dip"
    android:singleLine="true"
    android:layout_weight="1"
    android:layout_margin="0dp"
    />
   ```
2)NoteEditor.java中创建和修改时间的两个字段代码如下
```
private final void updateNote(String text, String title) {
    Date nowTime = new Date(System.currentTimeMillis());
   // System.out.println(System.currentTimeMillis());
    SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String retStrFormatNowDate = sdFormatter.format(nowTime);
    //System.out.println(retStrFormatNowDate);
```
3)NodeEditor.java中将时间存入数据库的代码如下所示
```
ContentValues values = new ContentValues();
values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, retStrFormatNowDate);
```
4)NoteList.java中的字段描述如下
```
adapter
    = new SimpleCursorAdapter(
              this,                             // The Context for the ListView
              R.layout.noteslist_item,          // Points to the XML for a list item
              cursor,                           // The cursor to get items from
              dataColumns,
              viewIDs,
              CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
      );

private String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE} ;

private int[] viewIDs = { R.id.text1,R.id.text2 };
```
截图如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/04df5b700dea4b70b8745c38092c1230.png#pic_center)


2.添加笔记查询功能
1)NodeList.java中查询功能的实现关键代码
```
private void SearchView(){
    searchView=findViewById(R.id.sv);
    searchView.onActionViewExpanded();
    searchView.setQueryHint("搜索笔记");
    searchView.setSubmitButtonEnabled(true);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if(!s.equals("")){
                String selection=NotePad.Notes.COLUMN_NAME_TITLE+" GLOB '*"+s+"*'";
                updatecursor = getContentResolver().query(
                        getIntent().getData(),            // Use the default content URI for the provider.
                        PROJECTION,                       // Return the note ID and title for each note.
                        selection,                             // No where clause, return all records.
                        null,                             // No where clause, therefore no where column values.
                        NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                );
                if(updatecursor.moveToNext())
                    Log.i("daawdwad",selection);
            }
           else {
                updatecursor = getContentResolver().query(
                        getIntent().getData(),            // Use the default content URI for the provider.
                        PROJECTION,                       // Return the note ID and title for each note.
                        null,                             // No where clause, return all records.
                        null,                             // No where clause, therefore no where column values.
                        NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                );
            }
            adapter.swapCursor(updatecursor);

           // adapter.notifyDataSetChanged();
            return false;
        }
    });
}
```
2)listview.xml中的控件
```
<android.support.v7.widget.SearchView
    android:id="@+id/sv"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    >
  ```

结果截图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/032dd66e94554da0a5244c1a83d2799c.png#pic_center)




3.背景美化：
关键代码:
```
android:background="@drawable/ht2"
android:background="@drawable/ht3"
```
截图如下:
![在这里插入图片描述](https://img-blog.csdnimg.cn/727e88029fd140cb8f94eec3a87f328a.png#pic_center)


4.字体大小更换
1)NoteEditor中添加如下代码:
```
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle all of the possible menu actions.
    switch (item.getItemId()) {
        case R.id.menu_save:
            String text = mText.getText().toString();
            updateNote(text, null);
            finish();
            break;
        case R.id.menu_delete:
            deleteNote();
            finish();
            break;
        case R.id.menu_revert:
            cancelNote();
            break;
        case R.id.red_font:
            mText.setTextColor(Color.RED);
            break;
        case R.id.black_font:
            mText.setTextColor(Color.BLACK);
        case R.id.font_10:
            mText.setTextSize(20);
            break;
        case R.id.font_16:
            mText.setTextSize(32);
            break;
        case R.id.font_20:
            mText.setTextSize(40);
            break;
    }
    return super.onOptionsItemSelected(item);
}
```
2)editor_options_menu.xml中添加如下代码:
```
<item android:title="字体大小">
    <menu>
        <group>
            <item android:id="@+id/font_10"
                android:title="小"/>
            <item android:id="@+id/font_16"
                android:title="中"/>
            <item android:id="@+id/font_20"
                android:title="大"/>
        </group>
    </menu>
</item>
```

截图如下所示:
![在这里插入图片描述](https://img-blog.csdnimg.cn/bacf339b11f1408c85e7a33043ede889.png#pic_center)


5.字体颜色更换
1)NoteEditor中添加如下代码:
```
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle all of the possible menu actions.
    switch (item.getItemId()) {
        case R.id.menu_save:
            String text = mText.getText().toString();
            updateNote(text, null);
            finish();
            break;
        case R.id.menu_delete:
            deleteNote();
            finish();
            break;
        case R.id.menu_revert:
            cancelNote();
            break;
        case R.id.red_font:
            mText.setTextColor(Color.RED);
            break;
        case R.id.black_font:
            mText.setTextColor(Color.BLACK);
        case R.id.font_10:
            mText.setTextSize(20);
            break;
        case R.id.font_16:
            mText.setTextSize(32);
            break;
        case R.id.font_20:
            mText.setTextSize(40);
            break;
    }
    return super.onOptionsItemSelected(item);
}
```
2)editor_options_menu.xml中添加如下代码:
```
<item
    android:title="字体颜色">
    <menu>
        <group>
            <item android:id="@+id/red_font"
                android:title="红色"/>
            <item android:id="@+id/black_font"
                android:title="黑色"/>
        </group>
    </menu>
</item>
```

截图如下所示:
![在这里插入图片描述](https://img-blog.csdnimg.cn/a8c6767f05d744ea93fbf1aa2f600080.png#pic_center)
6.排序功能
1)list_options_menu.xml中添加:
```
<item
    android:id="@+id/menu_sort"
    android:title="@string/menu_sort"
    android:icon="@android:drawable/ic_menu_sort_by_size"
    app:showAsAction="always" >
    <menu>
        <item
            android:id="@+id/menu_sort1"
            android:title="@string/menu_sort1"/>
        <item
            android:id="@+id/menu_sort2"
            android:title="@string/menu_sort2"/>

    </menu>
</item>
```
2)NoteList中增加两个选项
```
case R.id.menu_sort1:
        cursor = managedQuery(
                getIntent().getData(),            
                PROJECTION,                      
                null,                          
                null,                          
                NotePad.Notes._ID 
                );
        adapter = new MyCursorAdapter(
                this,
                R.layout.noteslist_item,
                cursor,
                dataColumns,
                viewIDs
        );
        setListAdapter(adapter);
        return true;

    case R.id.menu_sort2:
        cursor = managedQuery(
                getIntent().getData(),          
                PROJECTION,                      
                null,                            
                null,                       
                NotePad.Notes.DEFAULT_SORT_ORDER 
        );
        adapter = new MyCursorAdapter(
                this,
                R.layout.noteslist_item,
                cursor,
                dataColumns,
                viewIDs
        );
        setListAdapter(adapter);
```
截图如下:
![在这里插入图片描述](https://img-blog.csdnimg.cn/df9381e2a9684a90b1046dedffde52b2.png#pic_center)















































