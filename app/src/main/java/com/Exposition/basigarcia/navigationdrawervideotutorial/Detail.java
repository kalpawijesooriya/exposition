package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.Exposition.basigarcia.navigationdrawervideotutorial.R;


public class Detail extends AppCompatActivity {
    TextView topic,detail;
    ImageView picture;
    Toolbar toolbar = null;
    LinearLayout linner;
    LinearLayout linpic;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_detail);

        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar( toolbar );
        toolbar.setTitle("Details");

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        linner=(LinearLayout)findViewById(R.id.lin);
        picture=(ImageView) findViewById(R.id.pic);
        topic=(TextView)findViewById(R.id.topic);
        detail=(TextView)findViewById(R.id.detail);
        linpic=(LinearLayout)findViewById(R.id.linpic);

        Intent startactivity=getIntent();
        if(startactivity.hasExtra("movie")){
            String head=getIntent().getExtras().getString("movie");
            String story=getIntent().getExtras().getString("story");
            String image=getIntent().getExtras().getString("image");

            Glide.with(this)
                    .load(image)
                    .placeholder(R.drawable.load)
                    .into(picture);

            topic.setText(head);
            detail.setText(story);
            Log.i(" test","came here");
        }else{
            Toast.makeText(this,"No API Data",Toast.LENGTH_SHORT).show();
        }

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
       // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));


        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.itemdark)
                        {
                            linpic.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            linner.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            topic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            detail.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        }
                        if (id == R.id.itemlite)
                        {
                            linpic.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            linner.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            topic.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                            detail.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        }
                        if (id == R.id.itemzoomin)
                        {
                            double size= (int) detail.getTextSize();
                            size=size+1;
                            detail.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) size);
                        }
                        if (id == R.id.itemzoomout)
                        {   double size= (int) detail.getTextSize();
                            size=size-1;
                            detail.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) size);;
                        }
                        if (id == R.id.itemshare)
                        {   Intent myintent =new Intent(Intent.ACTION_SEND);
                            myintent.setType("text/plain");

                            TextView Body=(TextView)findViewById(R.id.detail);
                            String sharebody= (String) Body.getText();

                            TextView Topic=(TextView)findViewById(R.id.topic);
                            String sharetopic= (String) Topic.getText();

                            myintent.putExtra(Intent.EXTRA_SUBJECT,sharetopic);
                            myintent.putExtra(Intent.EXTRA_TEXT,sharebody);


                            startActivity(Intent.createChooser(myintent,"Share Via"));
                        }
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
//        transaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }




}
