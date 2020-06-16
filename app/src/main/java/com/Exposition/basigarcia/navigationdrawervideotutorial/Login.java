package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText mpassword,memail;
    private Button login,signup;
    private FirebaseAuth mAuth;
    private ProgressDialog mprograss;
    private DatabaseReference mref;
    private DatabaseReference mref2;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private static final String TAG = "Login";
    private Button googlebtn;
    private static int RC_SIGN_IN=1;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mpassword=(EditText)findViewById(R.id.passwordlogin);
        memail=(EditText)findViewById(R.id.emaillogin);
        login=(Button)findViewById(R.id.btnlogin);
        signup=(Button)findViewById(R.id.createacc);
        googlebtn=(Button)findViewById(R.id.googlebtn);

        mAuth=FirebaseAuth.getInstance();
        mref= FirebaseDatabase.getInstance().getReference().child("Users");
        mref2=FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth=FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(Login.this,"You got an error",Toast.LENGTH_LONG);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprograss.setMessage("Checking Login....");
                mprograss.show();
                signIn();
            }
        });


        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
            }
        };

        mprograss=new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checklogin();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent=new Intent(Login.this,Register.class);
                startActivity(loginintent);
                overridePendingTransition(R.anim.pull_in_right,R.anim.pull_in_left);
            }
        });
    }


    private void checklogin()
    {
        String email=memail.getText().toString().trim();
        String password=mpassword.getText().toString().trim();

        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)) {
            mprograss.setMessage("Checking Login....");
            mprograss.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mprograss.dismiss();
                        CheckExit();

                    }

                    else{
                        mprograss.dismiss();
                        Toast.makeText(Login.this,"Error Login",Toast.LENGTH_LONG).show();}
                }
            });

        }
    }

    private void CheckExit()
    {if(mAuth.getCurrentUser()!=null) {
        final String id = mAuth.getCurrentUser().getUid();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(id)) {
                    mprograss.dismiss();
                    Intent mainintent = new Intent(Login.this, MainActivity.class);
                    mainintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainintent);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.pull_in_left);
                } else {
                    Toast.makeText(Login.this, "You nedd to setup your account", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    }




    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
          mprograss.setMessage("Starting Sign in...");
           mprograss.show();
            if (result.isSuccess()){

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
             mprograss.dismiss();
             Toast.makeText(Login.this, "Error!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success :"+task.isSuccessful());
                            String id=mAuth.getCurrentUser().getUid();
                            DatabaseReference currentuser_id=mref2.child(id);
                            currentuser_id.child("name").setValue("someone");
                            mprograss.dismiss();
                            Intent mainintent=new Intent(Login.this,MainActivity.class);
                            mainintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mainintent);
                            overridePendingTransition(R.anim.pull_in_right,R.anim.pull_in_left);
                            //Toast.makeText(Login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            mprograss.dismiss();

                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            CheckExit();

                        }

                        mprograss.show();
                    }
                });
    }
}
