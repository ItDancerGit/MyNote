package com.example.note.mynote;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import android.content.Intent;

import java.util.ArrayList;

public class NoteActivity extends Fragment {
    private ListView noteListView;
    private SimpleDateFormat simpleDateFormat;
    private ArrayList<String> arrayTimes,arrayTitle,arrayContent;
    private String titles="",values="",nowTime="";
    private Intent intent;
    private MyBaseAdapter1 baseAdapter1;
    private boolean selfChange = false;
    private String saveTitle="",saveValue="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_layout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        arrayTitle = new ArrayList<String>();
        arrayTimes = new ArrayList<String>();
        arrayContent = new ArrayList<String>();
        noteListView = (ListView) getActivity().findViewById(R.id.notelv);
        baseAdapter1 = new MyBaseAdapter1();
        noteListView.setAdapter(baseAdapter1);
        initial();
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                titles = arrayTitle.get(position);
                values = arrayContent.get(position);
                intent = new Intent(getContext(),EditActivity.class);
                intent.putExtra("myTitle",titles);
                intent.putExtra("myValue",values);
                saveTitle = titles;
                saveValue = values;
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == 2){
                titles = data.getStringExtra("title");
                values = data.getStringExtra("content");
                selfChange = true;
                updateListView(titles,values);
            }
        }
        return;
    }

    private void initial(){
        arrayTimes.add("2011-1-1");
        arrayTitle.add("化学课堂笔记");
        arrayContent.add("这里是化学课堂笔记");
        arrayTimes.add("2011-2-2");
        arrayTitle.add("小抄");
        arrayContent.add("这里是我的小抄");
        arrayTimes.add("2011-3-3");
        arrayTitle.add("考试重点");
        arrayContent.add("这里是考试重点");
        arrayTimes.add("2011-4-4");
        arrayTitle.add("各科考纲");
        arrayContent.add("这里是各科考纲");
        arrayTimes.add("2011-5-5");
        arrayTitle.add("错题记录");
        arrayContent.add("这里是错题记录");
        arrayTimes.add("2011-6-6");
        arrayTitle.add("复习资料整理");
        arrayContent.add("这里是复习资料整理");
        arrayTimes.add("2011-7-7");
        arrayTitle.add("语文四字词");
        arrayContent.add("这里是语文四字词");
    }

    class MyBaseAdapter1 extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayTitle.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayTitle.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_list,parent,false);
                holder = new ViewHolder();
                holder.texts = (TextView) convertView.findViewById(R.id.list_text);
                holder.contents = (TextView) convertView.findViewById(R.id.list_texts);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.texts.setText(arrayTimes.get(position));
            holder.contents.setText(arrayTitle.get(position));
            return convertView;
        }
    }

    class ViewHolder{
        TextView texts;
        TextView contents;
    }

    public void updateListView(String title,String content){
        titles = title;
        values = content;
        if(!titles.equals("")){
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            nowTime = simpleDateFormat.format(new java.util.Date());
            if(selfChange){
                for(int i=0;i<arrayTitle.size();i++){
                    if(arrayTitle.get(i).equals(saveTitle)&&arrayContent.get(i).equals(saveValue)){
                        arrayTimes.remove(i);
                        arrayContent.remove(i);
                        arrayTitle.remove(i);
                        break;
                    }
                }
                selfChange = false;
            }
            arrayTimes.add(nowTime);
            arrayTitle.add(titles);
            arrayContent.add(values);
        }
        if(baseAdapter1 != null){
            baseAdapter1.notifyDataSetChanged();
            noteListView.setSelection(arrayTitle.size()-1);
        }
        else{
            baseAdapter1 = new MyBaseAdapter1();
            noteListView.setAdapter(baseAdapter1);
            noteListView.setSelection(arrayTitle.size()-1);
        }
    }

}
