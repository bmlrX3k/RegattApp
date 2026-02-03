package edu.regattapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class PathActivity extends AppCompatActivity {

    private final static String TAG = PathActivity.class.getName();

    private SharedPreferences sharedPreferences;

    private EditText num;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        this.num = super.findViewById(R.id.route_num);

        this.num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PathActivity.this.num.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PathActivity.this.start.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 0) {
                    PathActivity.this.start.setEnabled(false);
                }
            }
        });

        this.start = super.findViewById(R.id.start);
        this.start.setEnabled(false);

        String sharedPath = this.sharedPreferences.getString("path",null);

        if(sharedPath != null) {
            EditText route = (EditText) PathActivity.this.num;
            route.setText(sharedPath);
            this.start.setEnabled(true);
        }

        Intent intent = super.getIntent();

        String path = intent.getStringExtra("path");

        if(path != null) {
            EditText route = (EditText) PathActivity.this.num;
            route.setText(path);
            this.start.setEnabled(true);
        }

    }

    @Override
    protected void onStart() {
        Log.i(TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    public void update(View view) {
        Log.i(TAG,"update");
        try {
            new Sender(this,"192.168.1.164","path-request");
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void qrcode(View view) {
        Log.i(TAG,"qrcode");
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},0);
        }
        else {
            Intent intent = new Intent();
            intent.setClass(this,QrcodeActivity.class);
            super.startActivity(intent);
        }
    }

    public void start(View view) {
        Log.i(TAG,"start");
        String var = this.num.getText().toString();
        if (var.matches("\\d+")) {
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putString("path",var);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(this,NavActivity.class);
            super.startActivity(intent);
            super.finish();
        }
        else {
            Toast.makeText(this, R.string.path_error, Toast.LENGTH_SHORT).show();
        }
    }

}