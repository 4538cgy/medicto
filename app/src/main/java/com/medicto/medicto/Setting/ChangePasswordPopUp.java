package com.medicto.medicto.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.medicto.medicto.R;

public class ChangePasswordPopUp extends Activity {


    EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_password_pop_up);

        editTextPassword = (EditText)findViewById(R.id.change_password_popup_edittext);



    }

    public void mOnOK(View v){
        String password;
        password = editTextPassword.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("result","ok");
        intent.putExtra("password",password);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void mOnClose(View v){
        Intent intent = new Intent();
        intent.putExtra("result","Close Popup");
        setResult(RESULT_OK,intent);
        finish();
    }

    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return  false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }
}