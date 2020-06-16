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

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class IM_news extends AppCompatActivity {
    Toolbar toolbareditor = null;
    ImageView sathsara,erandi,basball,psan,invensio,infotel,fuchurex,rasoga,stylish,fintech,mass,sahasak,spandana,industry,Inspire,Training,HackaDev,BizMinds,Spandanashow,Colors;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_news);

        toolbareditor = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar(toolbareditor);
        toolbareditor.setTitle("IM News");

        sathsara=(ImageView) findViewById(R.id.sathsara);
        erandi=(ImageView) findViewById(R.id.erandi);
        basball=(ImageView) findViewById(R.id.basball);
        psan=(ImageView) findViewById(R.id.psan);
        invensio=(ImageView) findViewById(R.id.invensio);
        infotel=(ImageView) findViewById(R.id.infotel);
        fuchurex=(ImageView) findViewById(R.id.fuchurex);
        rasoga=(ImageView) findViewById(R.id.rasoga);
        stylish=(ImageView) findViewById(R.id.stylish);
        fintech=(ImageView) findViewById(R.id.fintech);
        mass =(ImageView) findViewById(R.id.mass);
        sahasak=(ImageView) findViewById(R.id. sahasak);
        spandana=(ImageView) findViewById(R.id.spandana);
        industry=(ImageView) findViewById(R.id.industry);
        Inspire=(ImageView) findViewById(R.id.Inspire);
        Training =(ImageView) findViewById(R.id.Training);
        HackaDev=(ImageView) findViewById(R.id.HackaDev);
        BizMinds=(ImageView) findViewById(R.id.BizMinds);
        Spandanashow=(ImageView) findViewById(R.id.Spandanashow);
        Colors=(ImageView) findViewById(R.id.Colors);


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imagesRef1 = storageRef.child("sathasara.png");
        StorageReference imagesRef2 = storageRef.child("erandi.jpg");
        StorageReference imagesRef3 = storageRef.child("basball.jpg");
        StorageReference imagesRef4 = storageRef.child("pasan.jpg");
        StorageReference imagesRef5 = storageRef.child("invensio.png");
        StorageReference imagesRef6 = storageRef.child("infotel.png");
        StorageReference imagesRef7 = storageRef.child("fuchurex.png");
        StorageReference imagesRef8 = storageRef.child("rasoga.png");
        StorageReference imagesRef9 = storageRef.child("stylish.jpg");
        StorageReference imagesRef10 = storageRef.child("imgimnews.jpg");
        StorageReference imagesRef11 = storageRef.child("mass.jpg");
        StorageReference imagesRef12 = storageRef.child("spandana.png");
        StorageReference imagesRef13 = storageRef.child("industry.png");
        StorageReference imagesRef14 = storageRef.child("Inspire.png");
        StorageReference imagesRef15 = storageRef.child("sahasak.jpg");
        StorageReference imagesRef16 = storageRef.child("Traing.png");
        StorageReference imagesRef17 = storageRef.child("HakaDev.png");
        StorageReference imagesRef18 = storageRef.child("BizMinds.png");
        StorageReference imagesRef19= storageRef.child("spandanashow.png");
        StorageReference imagesRef20 = storageRef.child("colors.png");
        loadimage(imagesRef1,imagesRef2,imagesRef3,imagesRef4,imagesRef5,imagesRef6,imagesRef7,imagesRef8,imagesRef9,imagesRef10,imagesRef11, imagesRef12,imagesRef13,imagesRef14,imagesRef15,imagesRef16, imagesRef17, imagesRef18, imagesRef19,imagesRef20);
    }

    private void loadimage(StorageReference imagesRef1,StorageReference imagesRef2,StorageReference imagesRef3,StorageReference imagesRef4,StorageReference imagesRef5,StorageReference imagesRef6,StorageReference imagesRef7,StorageReference imagesRef8,StorageReference imagesRef9,StorageReference imagesRef10,StorageReference imagesRef11,StorageReference imagesRef12,StorageReference imagesRef13,StorageReference imagesRef14,StorageReference imagesRef15,StorageReference imagesRef16,StorageReference imagesRef17,StorageReference imagesRef18,StorageReference imagesRef19,StorageReference imagesRef20)
    {
        imagesRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(sathsara);

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
                Picasso.with(getApplicationContext()).load(uri).into(erandi);

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
                Picasso.with(getApplicationContext()).load(uri).into(basball);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(psan);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef5.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(invensio);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef6.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(infotel);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef7.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(fuchurex);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef8.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(rasoga);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef9.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(stylish);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef10.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(fintech);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef11.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(mass);

            // Got the download URL for 'users/me/profile.png'
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef12.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(spandana);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef13.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(industry);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef14.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(Inspire);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef15.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(sahasak);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef16.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(Training);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef17.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(HackaDev);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef18.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(BizMinds);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef19.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(Spandanashow);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

        imagesRef20.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(Colors);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

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
