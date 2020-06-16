package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.Exposition.basigarcia.navigationdrawervideotutorial.R.color;
import com.Exposition.basigarcia.navigationdrawervideotutorial.R.layout;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by kalpa wijesooriya on 5/28/2017.
 */

public class Magazine extends AppCompatActivity  {

    private ImageView photo;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
PhotoViewAttacher mattach;

    private final String[] images = {

            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201701-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201702-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201703-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201704-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201705-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201706-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201707-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201708-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201709-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201710-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201711-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201712-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201713-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201714-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201715-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201716-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201717-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201718-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201719-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201720-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201721-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201722-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201723-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201724-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201725-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201726-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201727-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201728-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201729-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201730-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201731-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201732-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201733-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201734-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201735-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201736-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201737-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201738-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201739-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201740-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201741-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201742-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201743-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201744-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201745-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201746-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201747-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201748-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201749-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201750-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201751-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201752-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201753-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201754-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201755-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201756-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201757-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201758-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201759-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201760-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201761-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201762-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201763-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201764-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201765-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201766-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201767-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201768-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201769-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201770-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201771-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201772-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201773-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201774-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201775-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201776-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201777-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201778-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201779-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201780-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201781-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201782-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201783-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201784-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201785-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201786-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201787-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201788-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201789-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201790-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201791-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201792-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201793-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201794-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201795-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201796-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201797-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201798-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/Exposition201799-00.jpg",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/expo100.png",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/expo101.png",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/expo102.png",
            "https://raw.githubusercontent.com/kalpawijesooriya/aarticals/master/ExpositionIssue12/expo103.png"

    };


    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
        this.setContentView( layout.activity_magazine);
//        DownloadTask downloadTask=new DownloadTask();
//        downloadTask.execute( imageurl );
        this.photo =(ImageView) this.findViewById( R.id.photo );
        this.viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        this.adapter = new ViewPagerAdapter(this, this.images);
        this.viewPager.setAdapter(this.adapter);

        Toolbar toolbar= (Toolbar) this.findViewById(R.id.mytoolbar);
        this.setSupportActionBar( toolbar );
        toolbar.setTitle("Magazine");

        Window window = getWindow();
        window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, color.colorAccent));
        if(this.getSupportActionBar()!=null)
        {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        this.mattach =new PhotoViewAttacher(this.photo);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
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
