package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
private EditText mname,mpassword,memail;
private Button register;
private  FirebaseAuth mAuth;
private ProgressDialog mprograss;
private DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mname=(EditText)findViewById(R.id.name);
        mpassword=(EditText)findViewById(R.id.password);
        memail=(EditText)findViewById(R.id.email);
        register=(Button)findViewById(R.id.submit);

        mAuth=FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference().child("Users");
         mprograss=new ProgressDialog(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startregister();
            }
        });

    }

    private void startregister()
    {
        final String name=mname.getText().toString().trim();
        final String email=memail.getText().toString().trim();
        final String password=mpassword.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&  !TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)) {
            mprograss.setMessage("Signing Up....");
            mprograss.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        String id=mAuth.getCurrentUser().getUid();
                        DatabaseReference currentuser_id=mref.child(id);
                        currentuser_id.child("name").setValue(name);
                        currentuser_id.child("password").setValue(password);
                        currentuser_id.child("email").setValue(email);
                        mprograss.dismiss();
                        Intent mainintent=new Intent(Register.this,MainActivity.class);
                        mainintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainintent);
                        overridePendingTransition(R.anim.pull_in_right,R.anim.pull_in_left);
                    }
                }
            });

        }
    }
}
