package com.medicto.medicto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteActivity extends AppCompatActivity {

    Button yesButton,noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        yesButton = (Button)findViewById(R.id.delete_activity_button1);
        noButton = (Button)findViewById(R.id.delete_activity_button2);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("deleteCheck","delete");
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("deleteCheck","no");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}