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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonSeguir;
    TextView finalResult;
    ActivityResultLauncher<Intent> myARL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Unidades.class);

        finalResult = findViewById(R.id.finalResult);

        buttonSeguir = findViewById(R.id.buttonSeguir);
        buttonSeguir.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent = result.getData();
                            String total = intent.getStringExtra("Total").toString();
                            finalResult.setText(total);

                        } else if (result.getResultCode() == Activity.RESULT_CANCELED){
                            String error = "Sin mensaje de vuelta";
                            Context context = getApplicationContext();

                        }
                    }
                }
        );
    }
}