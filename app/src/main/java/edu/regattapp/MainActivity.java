package edu.regattapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void regatta(View view) {
        Log.d(TAG,"regatta");
        //Toast.makeText(this,R.string.test,Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.setClass(this,BoatConfActivity.class);
        super.startActivity(intent);

    }

    public void main(View view) {
        Log.d(TAG,"main");
        //Toast.makeText(this,R.string.test,Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.setClass(this,NavActivity.class);
        super.startActivity(intent);
        super.finish();

    }

}