package com.example.note.mynote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.*;

public class MyActivity extends Fragment {
    private ListView myListView;
    private int[] icons = {R.drawable.ic_perm_identity_black_24dp,R.drawable.ic_event_black_24dp
            ,R.drawable.ic_label_outline_black_24dp,R.drawable.ic_notifications_none_black_24dp};
    private String[] title = {"个人信息","备忘录","我的标签","消息中心"};
    private Button logins,changes,exits;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.my_layout,container,false);
        logins = (Button) view.findViewById(R.id.login_in);
        logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                startActivityForResult(intent,4);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        myListView = (ListView) getActivity().findViewById(R.id.mylv);
        MyBaseAdapter2 baseAdapter2 = new MyBaseAdapter2();
        myListView.setAdapter(baseAdapter2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 4){
            if(resultCode == 4){
                changes = (Button) getActivity().findViewById(R.id.change_button);
                exits = (Button) getActivity().findViewById(R.id.exit_button);
                logins.setVisibility(View.GONE);
                changes.setVisibility(View.VISIBLE);
                exits.setVisibility(View.VISIBLE);
            }
        }
        return;
    }

    class MyBaseAdapter2 extends BaseAdapter {
        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return title[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lvs_list,parent,false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
                holder.contents = (TextView) convertView.findViewById(R.id.text_view);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imageView.setBackgroundResource(icons[position]);
            holder.contents.setText(title[position]);
            return convertView;
        }
    }

    class ViewHolder{
        ImageView imageView;
        TextView contents;
    }
}
