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
    Button buttonUnidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centenas);
        Intent intent = new Intent(this, MainActivity.class);
        etCentenas = findViewById(R.id.etCentenas);
        String centenas =  etCentenas.getText().toString();

        buttonUnidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myARL.launch(intent);
            }
        });

        myARL = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent sendBack = new Intent();
                            sendBack.putExtra("Centenas", centenas);
                            setResult(RESULT_OK, sendBack);
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED){
                            String error = "Sin mensaje de vuelta";
                            Context context = getApplicationContext();
                        }
                    }
                }
        );
    }
}