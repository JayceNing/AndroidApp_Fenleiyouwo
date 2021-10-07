package com.example.fenleiyouwo.tabs;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fenleiyouwo.R;
import com.example.fenleiyouwo.recycleview.Garbage;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;

public class environmentFragment extends Fragment{

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_environment, container, false);
        EnvironmentAdapter environmentAdapter = new EnvironmentAdapter(this.getContext(), R.layout.environment_item, EnvironmentI.getAllEnvironments());

        ListView listView = (ListView) root.findViewById(R.id.environment_listView);

        listView.setAdapter(environmentAdapter);

        return root;
    }



}
