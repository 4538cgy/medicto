package com.medicto.medicto.Setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.medicto.medicto.R;
import com.medicto.medicto.SplashActivity;

public class Setting_AccountActivity extends AppCompatActivity implements View.OnClickListener{

    TextView email,deleteaccount,logout,title;

    Spinner spinner_Language,spinner_nationality,spinner_gender;


    Button button_save,button_password_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__account_info);


        email = (TextView)findViewById(R.id.activity_setting_account_info_textview_email);
        deleteaccount = (TextView)findViewById(R.id.activity_setting_account_info_textview_delete_account);
        logout = (TextView)findViewById(R.id.activity_setting_account_info_textview_logout);
        button_save = (Button)findViewById(R.id.activity_setting_account_info_button_save);
        button_password_change = (Button)findViewById(R.id.activity_setting_account_info_button_password);
        title = (TextView)findViewById(R.id.activity_setting_account_info_textview_title);
        button_password_change.setOnClickListener(this);

        logout.setOnClickListener(this);

        switch (getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
            case "ENGLISH":
                title.setText("MY ACCOUNT");
                deleteaccount.setText("DELETE ACCOUNT");
                button_password_change.setText("CHANGE PASSOWRD");
                logout.setText("LOG OUT");
                break;
            case "French":
                title.setText("Mon compte");
                deleteaccount.setText("Supprimer \nle compte");
                button_password_change.setText("Changement de \nmot de passe");
                logout.setText("Ouverture de\nsession");

                break;
            case "한국어":
                title.setText("내 계정");
                deleteaccount.setText("회원 탈퇴");
                button_password_change.setText("비밀번호 변경");
                logout.setText("로그아웃");

                break;
            case "汉语":
                title.setText("我的帐户");
                deleteaccount.setText("删除帐户");
                button_password_change.setText("更改密码");
                logout.setText("注销");

                break;
        }


        button_password_change.setOnClickListener(this);
        button_save.setOnClickListener(this);


        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());

        String[] list = getResources().getStringArray(R.array.nationality);
        String[] list2 = getResources().getStringArray(R.array.language);
        String[] list3 = getResources().getStringArray(R.array.gender);

        spinner_gender = (Spinner)findViewById(R.id.activity_setting_account_info_spinner);
        spinner_Language = (Spinner)findViewById(R.id.activity_setting_account_info_spinner2);
        spinner_nationality = (Spinner)findViewById(R.id.activity_setting_account_info_spinner3);


        spinner_gender.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list3));
        spinner_nationality.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list));
        spinner_Language.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list2));

        spinner_Language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void mOnPopupClick(View v){


        Intent intent = new Intent(this,DeleteAccountPopUp.class);
        intent.putExtra("data","Test Popup");
        startActivityForResult(intent,1);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                if (result.equals("ok")){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    AuthCredential credential = EmailAuthProvider
                            .getCredential(FirebaseAuth.getInstance().getCurrentUser().getEmail(),data.getStringExtra("password"));




                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                    }
                                }
                            });

                        }
                    });
                    AlertDialog.Builder builder = new AlertDialog.Builder(Setting_AccountActivity.this);
                    builder.setTitle(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()).setMessage("위 이메일로 비밀번호 변경 링크가 전송되었습니다.\n 변경된 비밀번호는 다음 로그인부터 적용됩니다.");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }

            }
        }

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                System.out.println("result의 결과 : "+result);
                if (result.equals("delete")){

                    System.out.println("계정 삭제");


                    System.out.println("사용자 계정" + FirebaseAuth.getInstance().getCurrentUser().getEmail());


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    AuthCredential credential = EmailAuthProvider
                            .getCredential(FirebaseAuth.getInstance().getCurrentUser().getEmail(),data.getStringExtra("password"));

                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    finishAffinity();

                                                }
                                            });

                                        }
                                    }
                                });
                            }
                        }
                    });





                    /*
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        finishAffinity();
                                    }
                                }
                            });



                     */
                }

            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){

            case R.id.activity_setting_account_info_button_password:
                 intent = new Intent(this,ChangePasswordPopUp.class);
                System.out.println("asdfasdfasdfasdfasdfasdfasdfasdf");
                startActivityForResult(intent,2);
                break;


            case R.id.activity_setting_account_info_button_save:

                switch (getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                    case "ENGLISH":
                        button_save.setText("CONFIRM");
                        break;
                    case "French":
                        button_save.setText("Confirmer");
                        break;
                    case "한국어":
                        button_save.setText("확인");
                        break;
                    case "汉语":
                        button_save.setText("确认");
                        break;
                }
                finish();
                break;


            case R.id.activity_setting_account_info_textview_logout:
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();



                SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                intent = new Intent(Setting_AccountActivity.this, SplashActivity.class);
                startActivity(intent);
                finishAffinity();

                break;
        }
    }
}