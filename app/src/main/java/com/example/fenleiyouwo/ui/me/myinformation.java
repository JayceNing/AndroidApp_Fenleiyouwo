package com.example.fenleiyouwo.ui.me;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fenleiyouwo.Data;
import com.example.fenleiyouwo.R;
import androidx.appcompat.app.AppCompatActivity;

public class myinformation extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);
        final Data app = (Data) getApplication();
        String name=app.getusername();
        String phone=app.getphone();

        TextView username=(TextView)findViewById(R.id.username);
        TextView phonen=(TextView)findViewById(R.id.phone);
        username.setText(name);
        phonen.setText(phone);

    }
}
