package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    private DatabaseReference mDatabaseusers;
    private KenBurnsView mKenBurns;
    private TextView mhackx,mmit,meditor,mnews,mstory;
    private Firebase mref;
    private CardView MITcard,Hackx2017,Editornote,IMNews,Sucess;
    ImageView imgimt,imgeditor,imghackx,imgimnews,imgsucsor;
    private RecyclerView recyclerView;
    private DatabaseReference myrefdatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        setAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        mAuth=FirebaseAuth.getInstance();
        mDatabaseusers=FirebaseDatabase.getInstance().getReference().child("Users");


        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                  Intent loginintent=new Intent(MainActivity.this,Login.class);
                  loginintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(loginintent);
                }

            }
        };



        imgeditor=(ImageView)findViewById(R.id.imged);
        imgimt=(ImageView)findViewById(R.id.imgmit);
        imgsucsor=(ImageView)findViewById(R.id.imgsucsot);
        imghackx=(ImageView)findViewById(R.id.imghacx);
        imgimnews=(ImageView)findViewById(R.id.imgimnews);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myrefdatabase= FirebaseDatabase.getInstance().getReference().child("/Blog");

        FirebaseRecyclerAdapter<Blog,BlogViewHolder> recyclerAdapter=new FirebaseRecyclerAdapter<Blog,BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                myrefdatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(model.getImage());
            }
        };
        recyclerView.setAdapter(recyclerAdapter);



        mAuth.addAuthStateListener(mAuthListner);


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imagesRef1 = storageRef.child("editor.png");
        StorageReference imagesRef2 = storageRef.child("mit.png");
        StorageReference imagesRef3 = storageRef.child("sucess.jpg");
        StorageReference imagesRef4 = storageRef.child("hackx.jpg");
        StorageReference imagesRef5 = storageRef.child("imgimnews.jpg");
       loadImages(imagesRef1,imagesRef2,imagesRef3,imagesRef4,imagesRef5);

        mref=new Firebase("https://navigationdrawervideotut-611bf.firebaseio.com/");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle( "Exposition" );

        mstory=(TextView)findViewById(R.id.sucstor);
        mmit=(TextView)findViewById(R.id.mitdic);
        mnews=(TextView)findViewById(R.id.imnews);
        meditor=(TextView)findViewById(R.id.ednote);
        mhackx=(TextView)findViewById(R.id.hackx);

    mref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Map<String,String> map=dataSnapshot.getValue(Map.class);

        String mit=map.get("MIT");
        String ed=map.get("editornote");
        String st=map.get("stories");
        String imn=map.get("news");
        String hack=map.get("hackx");

        mstory.setText(st);
        mmit.setText(mit);
        mnews.setText(imn);
        meditor.setText(ed);
        mhackx.setText(hack);

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
});





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mKenBurns = (KenBurnsView) findViewById(R.id.ken_burns_images);
        mKenBurns.setImageResource( R.drawable.mit);
        Timer timer = new Timer();
        MyTimer mt = new MyTimer();

        timer.schedule(mt, 7000, 7000);
//
        MITcard=(CardView)findViewById(R.id.Mit);
        Editornote=(CardView)findViewById(R.id.editornote);
        Sucess=(CardView)findViewById(R.id.Sucsuss);
        Hackx2017=(CardView)findViewById(R.id.Hackx2017);
        IMNews=(CardView)findViewById(R.id.IM_news);

        Editornote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EditorNote.class));
                overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);
            }
        });

        MITcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Mit.class));
                overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);
            }
        });

        Sucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Succes.class));
                overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);
            }
        });
        Hackx2017.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,hackx.class));
                overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);

            }
        });
        IMNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,IM_news.class));
                overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);

            }
        });




}

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);

    }

    public void loadImages(StorageReference imagesRef1, StorageReference imagesRef2, StorageReference imagesRef3, StorageReference imagesRef4, StorageReference imagesRef5) {

        imagesRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imgeditor);

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
                Picasso.with(getApplicationContext()).load(uri).into(imgimt);

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
                Picasso.with(getApplicationContext()).load(uri).into(imgsucsor);

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
                Picasso.with(getApplicationContext()).load(uri).into(imghackx);

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
                Picasso.with(getApplicationContext()).load(uri).into(imgimnews);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });


    }

    class MyTimer extends TimerTask {

        public void run() {

            runOnUiThread(new Runnable() {
                Random rand = new Random();
                public void run() {

                    int Images[] = { R.drawable.chamira, R.drawable.cha,R.drawable.mit, R.drawable.people, R.drawable.kancha, R.drawable.vidhya,R.drawable.it };
                    mKenBurns.setImageResource(Images[getRandomNumber()]);
                }

                private int getRandomNumber() {
                    // TODO Auto-generated method stub
                    return new Random().nextInt(7);}
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();




        return super.onOptionsItemSelected(item);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        TextView textView_decription;
        ImageView imageView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView_title = (TextView)itemView.findViewById(R.id.title);
            textView_decription = (TextView) itemView.findViewById(R.id.description);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
        public void setTitle(String title)
        {
            textView_title.setText(title);
        }
        public void setDescription(String description)
        {
            textView_decription.setText(description);
        }
        public void setImage(String image)
        {
            Picasso.with(mView.getContext())
                    .load(image)
                    .into(imageView);
        }



    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            //Set the fragment initially
            Intent search=new Intent(MainActivity.this,Magazine.class);
            startActivity(search);
            overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent search=new Intent(MainActivity.this,Gallery.class);
            startActivity(search);
            overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);


        } else if (id == R.id.nav_slideshow) {
            Intent search=new Intent(MainActivity.this,story.class);
            startActivity(search);
            overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);

        } else if (id == R.id.nav_manage) {
            Intent search=new Intent(MainActivity.this,Webpage.class);
            startActivity(search);
            overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);

        } else if (id == R.id.nav_share) {
            Intent search=new Intent(MainActivity.this,CardDemoActivity.class);
            startActivity(search);
            overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);

        } else if (id == R.id.nav_send) {
          startActivity(new Intent(this,Contact_us.class));
            overridePendingTransition(R.anim.pull_in_left,R.anim.pull_in_right);
        }
        else if (id == R.id.nav_log_out) {
            mAuth.addAuthStateListener(mAuthListner);
            FirebaseAuth.getInstance().signOut();
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setAnimation() {

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.setStartDelay(1000);
        animatorSet.start();
    }
        private static void delayforfivesecond() {
            try {
                Thread.sleep(3000);
            } catch (java.lang.InterruptedException iex) {
                System.out.print(iex);
            }
        }


}
