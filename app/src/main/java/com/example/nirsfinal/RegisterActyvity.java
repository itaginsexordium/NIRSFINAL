package com.example.nirsfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActyvity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference("users");
        }

        public void addIbase(View view) {
            EditText regName = (EditText) findViewById(R.id.addName);
            EditText regEmail= (EditText) findViewById(R.id.addEmail);
            EditText regPhone= (EditText) findViewById(R.id.addPhone);
            EditText regPassword=(EditText) findViewById(R.id.addPassword);


            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference("users");

            String name=regName.getEditableText().toString();
            String email=regEmail.getEditableText().toString();
            String phone=regPhone.getEditableText().toString();
            String password=regPassword.getEditableText().toString();
            UserModel singIn= new UserModel(name,email,phone,password);



            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users").child(email).child("info");

            myRef.child(name).setValue(singIn);
            registration(password,email);

        }
    public void registration(String email,String password){
             mAuth.createUserWithEmailAndPassword(email,password);
        }
}