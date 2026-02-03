package edu.regattapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class BoatConfActivity extends AppCompatActivity {

    private final static String TAG = BoatConfActivity.class.getName();

    private SharedPreferences sharedPreferences;

    private Spinner type;
    private EditText model;
    private EditText sail;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_conf);

        this.sail = super.findViewById(R.id.sail_num);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();
        boolean modifyConf = intent.getBooleanExtra("modifyConf",false);

        Log.i(TAG,"modifyConf = " + modifyConf);

        if(!modifyConf) {
            String sharedType = this.sharedPreferences.getString("type",null);
            String sharedModel = this.sharedPreferences.getString("model",null);
            String sharedSail = this.sharedPreferences.getString("sail",null);

            Log.i(TAG,"Shared configuration = " + sharedType + " / " + sharedModel + " / " + sharedSail);

            if(sharedType != null && sharedModel != null &&  sharedSail != null) {
                this.nextActivity();
            }
        }

        this.sail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                BoatConfActivity.this.sail.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                BoatConfActivity.this.save.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 0) {
                    BoatConfActivity.this.save.setEnabled(false);
                }
            }
        });

        this.save = super.findViewById(R.id.save);
        this.save.setEnabled(false);
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

    public void save(View view) {
        Log.i(TAG,"save");
        this.type = super.findViewById(R.id.type_spin);
        this.model = super.findViewById(R.id.model_fill);
        if(this.model.length() != 0) {
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putString("type",this.type.getSelectedItem().toString());
            editor.putString("model",this.model.getText().toString());
            editor.putString("sail",this.sail.getText().toString());
            editor.commit();

            this.nextActivity();
        }
        else {
            this.model.setError(super.getString(R.string.unfilled));
        }
    }

    public void nextActivity() {
        Intent intent = new Intent();
        intent.setClass(this,PathActivity.class);
        super.startActivity(intent);
    }

}