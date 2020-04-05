package com.example.nirsfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText inpassword,inMail;
    private TextView messageText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user == null) {

                } else {

                }
            }
        };



        inMail = (EditText) findViewById(R.id.username);
        inpassword = (EditText) findViewById(R.id.password);
        messageText = (TextView) findViewById(R.id.textUnderPassword);
    }



    public void login(View view) {
        singIn(inMail.getText().toString(),inpassword.getText().toString());

    }

    public void register(View view) {
        intent=new Intent(this,RegisterActyvity.class);

    }

public void singIn(final String email, String password){
mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            messageText.setText("Авторизация успешна ");
            intent= new Intent(LoginActivity.this , MainActivity.class);
            intent.putExtra(MainActivity.EXTRA_USERNAME,email);
            startActivity(intent);
        }else {
            messageText.setText("Авторизация провалена");}

    }
});
}


   // public void registration(String email,String password){
   //     mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
   //         @Override
   //         public void onComplete(@NonNull Task<AuthResult> task) {
   //             if (task.isSuccessful()) {
   //                 messageText.setText("регистрация успешна ");
  //              }else {
 //                   messageText.setText("регистрация провалена");
//
//                }
//
//
  //          }
  //      });
//
    //}







}
