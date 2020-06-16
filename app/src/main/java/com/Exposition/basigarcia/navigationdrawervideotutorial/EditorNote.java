package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class EditorNote extends AppCompatActivity {
    Toolbar toolbareditor = null;
    private Firebase mref;
    CheckedTextView txt1,txt2,txt3,txt4,txt5,txt6;
    ImageView photo,issue12;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_note);

        toolbareditor = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar(toolbareditor);
        toolbareditor.setTitle( "Editors' note" );


        photo=(ImageView)findViewById(R.id.imgeditor);
        issue12=(ImageView)findViewById(R.id.isu12);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imagesRef = storageRef.child("Vihanga.png");
        StorageReference imagesRefissu12 = storageRef.child("issue12.jpg");


        loadImages(imagesRefissu12, imagesRef );

        mref=new Firebase("https://navigationdrawervideotut-611bf.firebaseio.com/Editor note");
        txt1=(CheckedTextView)findViewById(R.id.txt1);
        txt2=(CheckedTextView)findViewById(R.id.txt2);
        txt3=(CheckedTextView)findViewById(R.id.txt3);
        txt4=(CheckedTextView)findViewById(R.id.txt4);
        txt5=(CheckedTextView)findViewById(R.id.txt5);
        txt6=(CheckedTextView)findViewById(R.id.txt6);



        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String para1 = dataSnapshot.child("1srpara").getValue(String.class);
                txt1.setText(para1);
                String para2 = dataSnapshot.child("2ndpara").getValue(String.class);
                txt2.setText(para2);
                String para3 = dataSnapshot.child("3rdpara").getValue(String.class);
                txt3.setText(para3);
                String para4 = dataSnapshot.child("4thpara").getValue(String.class);
                txt4.setText(para4);
                String para5 = dataSnapshot.child("5thpara").getValue(String.class);
                txt5.setText(para5);
                String para6 = dataSnapshot.child("6thpara").getValue(String.class);
                txt6.setText(para6);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

   public void loadImages(StorageReference imagesRefissu12, StorageReference imagesRef){
       imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
           @Override
           public void onSuccess(Uri uri) {
               Picasso.with(getApplicationContext()).load(uri).into(photo);

               // Got the download URL for 'users/me/profile.png'
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception exception) {

           }
       });


       imagesRefissu12.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
           @Override
           public void onSuccess(Uri uri) {
               Picasso.with(getApplicationContext()).load(uri).into(issue12);

               // Got the download URL for 'users/me/profile.png'
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception exception) {
               // Handle any errors
           }
       });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }


}
