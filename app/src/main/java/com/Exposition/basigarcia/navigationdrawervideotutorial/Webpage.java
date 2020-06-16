package com.Exposition.basigarcia.navigationdrawervideotutorial;

/**
 * Created by kalpa wijesooriya on 5/28/2017.
 */

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;

public class Webpage extends AppCompatActivity {
    WebView  mWebview ;
    private ProgressDialog mprograss;
    //Toolbar toolbar = null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_webpage);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar( toolbar );
        toolbar.setTitle("Web page");
      //  mprograss=new ProgressDialog(this);

        final ProgressDialog pd = ProgressDialog.show(Webpage.this, "", "Please wait, page is Loading...", true);

        mWebview  = (WebView)findViewById(R.id.web);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.setFocusable( true );
         mWebview.setFocusableInTouchMode( true );
         mWebview.getSettings().setRenderPriority( WebSettings.RenderPriority.HIGH);
          mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
         mWebview.getSettings().setDomStorageEnabled( true );
          mWebview.getSettings().setDatabaseEnabled( true );
         mWebview.getSettings().setAppCacheEnabled( true );
          mWebview.setScrollBarStyle( View.SCROLLBARS_INSIDE_OVERLAY);
          mWebview.setWebViewClient(new WebViewClient());

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Webpage.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();

                String webUrl = mWebview.getUrl();

            }



    });
        mWebview .loadUrl("http://www.mit.kln.ac.lk/exposition2017");

//        wv=(WebView)findViewById(R.id.web);
//        wv.getSettings().setJavaScriptEnabled(true);
//
//        mprograss.setMessage("Loading... please wait");
//        mprograss.show();
//        wv.loadUrl("http://www.mit.kln.ac.lk/exposition2016/");

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_demo, menu);
        return true;
    }







}
