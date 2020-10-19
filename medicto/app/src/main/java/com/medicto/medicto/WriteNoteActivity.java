package com.medicto.medicto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WriteNoteActivity extends AppCompatActivity {

    ImageButton imageButton;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_addnote);
        button = (Button)findViewById(R.id.activity_write_addnote_button);
        imageButton = (ImageButton)findViewById(R.id.activity_write_addnote_imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //노트 데이터 업로드

                finish();
            }
        });

    }
}
