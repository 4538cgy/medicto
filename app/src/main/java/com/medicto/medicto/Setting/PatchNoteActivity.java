package com.medicto.medicto.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.medicto.medicto.R;

public class PatchNoteActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch_note);
        textView = (TextView)findViewById(R.id.activity_patch_note_textview);

        textView.setText("2020-07-30 [ Setting Initialized ] " +
                "\n" +
                "2020-07-29 [ Nope ]");
    }
}