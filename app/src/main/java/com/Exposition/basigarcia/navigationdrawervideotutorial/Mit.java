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
import android.widget.ImageView;
import android.widget.TextView;

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

public class Mit extends AppCompatActivity {
    Toolbar toolbareditor = null;
    ImageView howmit,department,mitimage;
    TextView mitpara1,mitpara2;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mit);



        toolbareditor = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar(toolbareditor);
        toolbareditor.setTitle( "Editors' note" );

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imagesRef1 = storageRef.child("mit.png");
        StorageReference imagesRef2 = storageRef.child("mit4.png");
        StorageReference imagesRef3 = storageRef.child("mit7.png");


        Firebase mref=new Firebase("https://navigationdrawervideotut-611bf.firebaseio.com/MITnote");

        howmit=(ImageView)findViewById(R.id.howmit);
        department=(ImageView)findViewById(R.id.deparment);
        mitimage=(ImageView)findViewById(R.id.mitimage);
        mitpara1=(TextView)findViewById(R.id.mitpara1);
        mitpara2=(TextView)findViewById(R.id.mitpara2);

        loadimage(imagesRef1,imagesRef2,imagesRef3);
        loadtext(mref);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }

    private void loadimage(StorageReference imagesRef1,StorageReference imagesRef2,StorageReference imagesRef3)
    {
        imagesRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(mitimage);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(howmit);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(department);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

    }
    private  void loadtext(Firebase mref)
    {
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String para1 = dataSnapshot.child("1stpara").getValue(String.class);
                mitpara1.setText(para1);
                String para2 = dataSnapshot.child("3rdpara").getValue(String.class);
                mitpara2.setText(para2);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}
