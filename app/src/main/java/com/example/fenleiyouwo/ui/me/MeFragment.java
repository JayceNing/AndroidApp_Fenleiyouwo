package com.example.fenleiyouwo.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fenleiyouwo.Data;
import com.example.fenleiyouwo.MainActivity;
import com.example.fenleiyouwo.R;
import com.example.fenleiyouwo.knowLedge;
import com.example.fenleiyouwo.place;
import com.example.fenleiyouwo.ui.home.HomeFragment;
import com.qmuiteam.qmui.util.QMUIPackageHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MeFragment extends Fragment {


    private List<Items> itemlist=new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        final Data app = (Data) getActivity().getApplication();
        String user_id=app.getid();
        String water=app.getwater();
        String fertilizer=app.getfertilizer();
        String tree_progress=app.gettree_progress();
        Button login = (Button) getActivity().findViewById(R.id.login);
        TextView textView=(TextView) getActivity().findViewById(R.id.tips);
        if(user_id==null){
            textView.setText("未登录");
            login.setVisibility(View.VISIBLE);
        }else{
            textView.setText(app.getusername());
            login.setVisibility(View.GONE);
        }
        ItemsAdapter adapter=new ItemsAdapter(this.getContext(),R.layout.itemlayout,itemlist);
        ListView listview=(ListView) getActivity().findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items items=itemlist.get(position);

                switch (items.getId())
                {
                    case 1:
                        if(user_id==null){Toast.makeText(getContext(),"用户未登录",Toast.LENGTH_SHORT).show();}
                        else{
                            Intent intent=new Intent(getActivity(), myinformation.class);
                            startActivity(intent);
                        }

                        break;
                    case 2:
                        if(user_id==null){Toast.makeText(getContext(),"用户未登录",Toast.LENGTH_SHORT).show();}
                        else{
                            Intent intent1=new Intent(getActivity() ,  MessageActivity.class);
                            intent1.putExtra("id",user_id);
                            startActivity(intent1);}
                        break;
                    case 3:
                        if(user_id==null){Toast.makeText(getContext(),"用户未登录",Toast.LENGTH_SHORT).show();}
                        else{
                            Intent intent2 = new Intent(getActivity(), TreeActivity.class);
                            intent2.putExtra("id",user_id);
                            intent2.putExtra("water",water);
                            intent2.putExtra("fertilizer",fertilizer);
                            intent2.putExtra("tree_progress",tree_progress);
                            startActivity(intent2);}
                        break;
                    case 4:
                        Toast.makeText(getContext(),"V0.9.1",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_me, container, false);

        Button login = (Button) root.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String username=user.getUsername().toString();

                Intent intent=new Intent(getActivity(), MeActivity.class);
                startActivity(intent);
            }
        });

        final Data app = (Data) getActivity().getApplication();
        String user_id=app.getid();
        String water=app.getwater();
        String fertilizer=app.getfertilizer();
        String tree_progress=app.gettree_progress();

        TextView textView=(TextView) root.findViewById(R.id.tips);
        ImageView imageView=(ImageView) root.findViewById(R.id.touxiang);
        ImageView imageView2=(ImageView) root.findViewById(R.id.logo);
        imageView.setImageResource(R.drawable.touxiang);
        imageView2.setImageResource(R.drawable.about_logo);
        if(user_id==null){
            textView.setText("未登录");
            login.setVisibility(View.VISIBLE);
        }else{
            textView.setText(app.getusername());
            login.setVisibility(View.GONE);
        }

        inititems();
        ItemsAdapter adapter=new ItemsAdapter(this.getContext(),R.layout.itemlayout,itemlist);
        ListView listview=(ListView) root.findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items items=itemlist.get(position);

                switch (items.getId())
                {
                    case 1:
                        if(user_id==null){Toast.makeText(getContext(),"用户未登录",Toast.LENGTH_SHORT).show();}
                        else{
                            Intent intent=new Intent(getActivity(), myinformation.class);
                            startActivity(intent);
                        }

                        break;
                    case 2:
                        if(user_id==null){Toast.makeText(getContext(),"用户未登录",Toast.LENGTH_SHORT).show();}
                        else{
                        Intent intent1=new Intent(getActivity() ,  MessageActivity.class);
                        intent1.putExtra("id",user_id);
                        startActivity(intent1);}
                        break;
                    case 3:
                        if(user_id==null){Toast.makeText(getContext(),"用户未登录",Toast.LENGTH_SHORT).show();}
                        else{
                        Intent intent2 = new Intent(getActivity(), TreeActivity.class);
                        intent2.putExtra("id",user_id);
                        intent2.putExtra("water",water);
                        intent2.putExtra("fertilizer",fertilizer);
                        intent2.putExtra("tree_progress",tree_progress);
                        startActivity(intent2);}
                        break;
                    case 4:
                        Toast.makeText(getContext(),"V0.9.1",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return root;
    }


    private void inititems() {
        Items one=new Items("我的信息",R.drawable.pone,1);
        itemlist.add(one);
        Items two=new Items("垃圾投放记录",R.drawable.ptwo,2);
        itemlist.add(two);
        Items three=new Items("我的种树",R.drawable.ptwo,3);
        itemlist.add(three);
        Items four=new Items("版本",R.drawable.pthree,4);
        itemlist.add(four);
        Items five=new Items("帮助",R.drawable.pfour,5);
        itemlist.add(five);
    }

}
