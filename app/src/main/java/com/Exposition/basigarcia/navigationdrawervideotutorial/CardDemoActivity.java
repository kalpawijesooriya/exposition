package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CardDemoActivity extends AppCompatActivity {
    private DatabaseReference myrefdatabase;
    RecyclerView recyclerView;
    Toolbar toolbar = null;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_card_demo);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewmem);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarappbar);
        setSupportActionBar( toolbar );
        toolbar.setTitle("Members");
        mAuth=FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myrefdatabase= FirebaseDatabase.getInstance().getReference().child("/Members");
        FirebaseRecyclerAdapter<member,BlogViewHolder> recyclerAdapter=new FirebaseRecyclerAdapter<member,BlogViewHolder>(
                member.class,
                R.layout.card_layoutmember,
                BlogViewHolder.class,
                myrefdatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, member model, int position) {
                viewHolder.setname(model.getname());
                viewHolder.setposition(model.getposition());
                viewHolder.setcontactno(model.getcontactno());
                viewHolder.setImage(model.getImage());
            }
        };
        recyclerView.setAdapter(recyclerAdapter);




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
    protected void onStart() {
        super.onStart();
       // mAuth.addAuthStateListener(mAuthListner);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_demo, menu);
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

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtname;
        TextView txtposition,txtcontactno;

        ImageView imageView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            txtname = (TextView)itemView.findViewById(R.id.membername);
            txtposition = (TextView) itemView.findViewById(R.id.position);
            txtcontactno = (TextView) itemView.findViewById(R.id.contactno);
            imageView=(ImageView)itemView.findViewById(R.id.memimg);
        }
        public void setname(String name)
        {
            txtname.setText(name);
        }
        public void setposition(String position)
        {
            txtposition.setText(position);
        }
        public void  setcontactno(String contactno)
        {
            txtcontactno.setText(contactno);
        }
        public void setImage(String image)
        {
            Picasso.with(mView.getContext())
                    .load(image)
                    .into(imageView);
        }



    }

}
