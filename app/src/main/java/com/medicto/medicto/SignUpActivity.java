package com.medicto.medicto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.medicto.medicto.model.UserModel;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    Spinner spinner1,spinner2,spinner3;
    ImageButton profileImage;
    Uri imageUri;
    EditText id,password,name;
    String nationality = "KOREA",lang = "KOREA",gender = "Male";
    boolean accept;
    private static final int PICK_FROM_ALBUM = 10;
    String language = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseAuth.getInstance().signOut();
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        nationality = "KOREA";
        lang = "KOREA";
        gender = "Male";
        imageUri = null;
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        Intent intent = getIntent();
        language = intent.getStringExtra("language");



        profileImage = (ImageButton) findViewById(R.id.activity_sign_up_imagebutton);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent,PICK_FROM_ALBUM);
            }
        });



        id = (EditText)findViewById(R.id.activity_sign_up_edittext);
        password = (EditText)findViewById(R.id.activity_sign_up_edittext2);
        name = (EditText)findViewById(R.id.activity_sign_up_edittext3);



        spinner1 = (Spinner)findViewById(R.id.activity_sign_up_spinner11);
        spinner2 = (Spinner)findViewById(R.id.activity_sign_up_spinner21);
        spinner3 = (Spinner)findViewById(R.id.activity_sign_up_spinner31);

        String[] list = getResources().getStringArray(R.array.nationality);
        String[] list2 = getResources().getStringArray(R.array.language);
        String[] list3 = getResources().getStringArray(R.array.gender);

        spinner1.setAdapter(new ArrayAdapter<>(this,R.layout.item_spinner,list));
        spinner2.setAdapter(new ArrayAdapter<>(this,R.layout.item_spinner,list2));
        spinner3.setAdapter(new ArrayAdapter<>(this,R.layout.item_spinner,list3));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nationality = spinner1.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lang = spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = spinner3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button = (Button)findViewById(R.id.activity_sign_up_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(name.getText().toString());
                System.out.println(id.getText().toString());
                System.out.println(password.getText().toString());
                System.out.println(nationality);
                System.out.println(gender);
                System.out.println(lang);
                System.out.println(accept);
                System.out.println(imageUri);

                onSignUp();







            }
        });

        switch (language){
            case "한국어":
                button.setText("확인");
                break;
            case "ENGLISH":
                button.setText("Confirm");
                break;
            case "French":
                button.setText("Confirmer");
                break;
            case"汉语":
                button.setText("确认");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK){
            profileImage.setImageURI(data.getData());
            imageUri = data.getData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_up_togglebutton:
                if (accept == true){
                    accept = false;
                }else{
                    accept = true;
                }
                break;
        }
    }

    void onSignUp(){

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);

        progressDialog.setTitle("계정 생성중 \n앱을 종료하지 마세요.");

        progressDialog.show();

        if (name.getText().toString() == null || id.getText().toString() == null || password.getText().toString() == null) {

            return;
        }

        else if (imageUri == null) {
            Toast.makeText(getApplicationContext(), "Please Pick your profile image.", Toast.LENGTH_SHORT).show();
            return;

        } else if (id.getText().toString() == null) {
            id.setError("Please enter a email");
            return;
        } else if (!(id.getText().toString()).contains("@")) {
            id.setError("Email format does not match.");
            return;
        } else if (name.getText().toString() == null) {
            name.setError("Please enter a name");
            return;
        } else if (password.getText().length() < 5) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }
                /*
                else if(accept == false){
                    Toast.makeText(getApplicationContext(),"If you do not agree to the terms and conditions, you cannot sign up.",Toast.LENGTH_SHORT).show();
                    return;
                }

                 */

        else{
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(id.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {









                            final String uid = task.getResult().getUser().getUid();


                            FirebaseStorage.getInstance().getReference().child("userImages").child(uid).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    String imageUri = task.getResult().getStorage().getDownloadUrl().toString();


                                    UserModel userModel = new UserModel();
                                    userModel.userName = name.getText().toString();
                                    userModel.profileImageUrl = imageUri;
                                    userModel.userGender = gender;
                                    userModel.userLanguage = lang;
                                    userModel.userNationality = nationality;
                                    userModel.emailAdress = id.getText().toString();
                                    userModel.password = password.getText().toString();
                                    userModel.signUpTime = new Get_time().getTime();

                                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);

                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                    progressDialog.dismiss();
                                    finishAffinity();


                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                }
            }).addOnCanceledListener(new OnCanceledListener() {
                @Override
                public void onCanceled() {
                    Toast.makeText(getApplicationContext(),"회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}