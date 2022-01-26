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

public class Centenas extends AppCompatActivity {

    ActivityResultLauncher<Intent> myARL;
    EditText etCentenas;
    Button buttonCentenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centenas);
        etCentenas = findViewById(R.id.etCentenas);
        buttonCentenas = findViewById(R.id.buttonCentenas);


        buttonCentenas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String centenas =  etCentenas.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("Centenas", centenas);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}