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

public class Unidades extends AppCompatActivity {
    ActivityResultLauncher<Intent> myARL;
    EditText etUnidades;
    Button buttonUnidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);
        etUnidades = findViewById(R.id.etUnidades);
        buttonUnidades = findViewById(R.id.buttonUnidades);

        buttonUnidades.setOnClickListener(new View.OnClickListener() {
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
                            //Recuperar decenas y centenas
                            Intent intentRetorno = result.getData();
                            String centenas = intentRetorno.getStringExtra("Centenas").toString();
                            String decenas = intentRetorno.getStringExtra("Decenas").toString();

                            //Leer unidades
                            String unidades =  etUnidades.getText().toString();

                            //Construir retorno
                            String total = centenas+decenas+unidades;
                            Intent intent = new Intent();
                            intent.putExtra("Total", total);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                }
        );
    }

    public void launchActivity (View v){
        Intent intent = new Intent(this, Decenas.class);
        myARL.launch(intent);
    }
}