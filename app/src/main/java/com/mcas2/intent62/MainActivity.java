package com.mcas2.intent62;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button buttonSeguir;
    Button buttonSalir;
    TextView finalResult;
    TextView binaryResult;
    ActivityResultLauncher<Intent> myARL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finalResult = findViewById(R.id.finalResult);
        binaryResult = findViewById(R.id.binaryResult);

        buttonSeguir = findViewById(R.id.buttonSeguir);
        buttonSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v);
            }
        });

        buttonSalir = findViewById(R.id.buttonSalir);
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        myARL = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent intent = result.getData();
                            String total = intent.getStringExtra("Total").toString();
                            finalResult.setText(total);
                            Integer totalInt = Integer.parseInt(total);
                            String binario = Integer.toBinaryString(totalInt);
                            binaryResult.setText(binario);
                        }
                    }
                }
        );
    }

    public void launchActivity (View v){
        Intent intent = new Intent(this, Unidades.class);
        myARL.launch(intent);
    }

    public void exit(){
        this.finish();
    };
}