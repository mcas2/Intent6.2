package com.mcas2.intent62;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Decenas extends AppCompatActivity {
    ActivityResultLauncher<Intent> myARL;
    EditText etDecenas;
    Button buttonDecenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decenas);
        etDecenas = findViewById(R.id.etDecenas);
        buttonDecenas = findViewById(R.id.buttonDecenas);


        buttonDecenas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v);
            }
        });

        myARL = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            String decenas =  etDecenas.getText().toString();

                            Intent intentRetorno = result.getData();
                            String centenas = intentRetorno.getStringExtra("Centenas").toString();

                            Intent intent = new Intent();
                            intent.putExtra("Centenas", centenas);
                            intent.putExtra("Decenas", decenas);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                }
        );
    }

    public void launchActivity (View v){
        Intent intent = new Intent(this, Centenas.class);
        myARL.launch(intent);
    }
}