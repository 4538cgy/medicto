package com.medicto.medicto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medicto.medicto.Fragment.Main_Fragment_Input_UserInfo;
import com.medicto.medicto.model.UserModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Button loginbutton;
    Spinner spinner;


    private EditText id;
    private EditText password;

    private TextView textView;

    private FirebaseRemoteConfig firebaseRemoteConfig;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    boolean loginCheck = false;

    String standardLanguage = null;

    TextView textViewTitle;
    String language = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = (Spinner)findViewById(R.id.activity_login_spinner_language_selector);

        loginbutton = (Button)findViewById(R.id.activity_login_button_login);
        loginbutton.setOnClickListener(this);

        textView = (TextView)findViewById(R.id.activity_login_textview);
        textView.setOnClickListener(this);

        textViewTitle = (TextView)findViewById(R.id.activity_login_textview_title);
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
      // firebaseAuth.signOut();

        id = (EditText)findViewById(R.id.activity_login_edittext_id);
        password = (EditText)findViewById(R.id.activity_login_edittext_password);

        String[]list = getResources().getStringArray(R.array.language);
        spinner.setAdapter(new ArrayAdapter<>(this,R.layout.item_spinner,list));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                language = spinner.getSelectedItem().toString();
                initializedTextView(language);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //로그인
                    System.out.println("LoginActivity = 사용자 정보 가져오기 실행 (1/3)" );


                    Intent intent = new Intent(LoginActivity.this,LobbyActivity.class);
                    startActivity(intent);
                    finish();




                }else{
                    //로그아웃

                }
            }
        };




    }

    void initializedTextView(String a){
        switch (a){
            case "한국어":
                textViewTitle.setText("Medicto에 오신걸 환영합니다.");
                id.setHint("Email Address");
                password.setHint("Password");
                loginbutton.setText("로그인");
                textView.setText("회원 가입");
                standardLanguage = "한국어";
                break;
            case "ENGLISH":
                textViewTitle.setText("WELCOME TO MEDICTO");
                id.setHint("Email Address");
                password.setHint("Password");
                loginbutton.setText("LOGIN");
                textView.setText("Create an account");
                standardLanguage = "ENGLISH";
                break;
            case "French":
                textViewTitle.setText("BIENVENUE AU MÉDECIN");
                id.setHint("Email Address");
                password.setHint("Password");
                loginbutton.setText("LOGIN");
                textView.setText("Créer un compte");
                standardLanguage = "French";
                break;
            case"汉语":
                textViewTitle.setText("致母乳的问候");
                id.setHint("Email Address");
                password.setHint("Password");
                loginbutton.setText("登入");
                textView.setText("創建帳戶");
                standardLanguage = "汉语";
                break;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){




            case R.id.activity_login_button_login:

                if (id.getText().toString().equals("") || id.getText().toString() == null){

                    id.setError("Please enter your ID.");

                }else if( password.getText().toString().equals("")|| password.getText().toString() == null){

                    password.setError("Please enter your PASSWORD.");

                }
                else if(language.toString().equals(null)){

                }
                else{
                    loginEvent();
                }



                break;

            case R.id.activity_login_textview:



                SignUp();
                break;
        }
    }



    void SignUp(){
        Intent intent = new Intent(getApplicationContext(), AgreementActivity.class);
        intent.putExtra(id.getText().toString(),"id");
        intent.putExtra(password.getText().toString(),"password");
        intent.putExtra("language",standardLanguage);
        startActivity(intent);
    }

    void loginEvent(){
        firebaseAuth.signInWithEmailAndPassword(id.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (!task.isSuccessful()){
                    //로그인이 실패했을때
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("로그인 성공");
                    System.out.println(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userData_Lang", language);
                    editor.commit();
                }
                firebaseAuth.getCurrentUser().getUid();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


}