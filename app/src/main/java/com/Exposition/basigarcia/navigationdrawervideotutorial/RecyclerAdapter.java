package com.Exposition.basigarcia.navigationdrawervideotutorial;

/**
 * Created by Shade on 5/9/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    private FirebaseAuth mAuth;


    public RecyclerAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    private List<ListItem> listItems;
   private Context context;
   public boolean mprocesslike = false;




    public class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageView itemImage;
        public RelativeLayout relativeLayout;
        public TextView textViewHead;
        public TextView textViewDesc,likeamount;
        public TextView topic,detail;
        private Firebase mref;
        ImageView mbtnlike;
        TextView mtxtlike;
        Firebase alike;



        public ViewHolder(View itemView) {
            super(itemView);

            mAuth=FirebaseAuth.getInstance();

            relativeLayout=(RelativeLayout) itemView.findViewById( R.id.relativeLayout);
            textViewHead = (TextView)itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView)itemView.findViewById(R.id.textViewDesc);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            topic = (TextView)itemView.findViewById(R.id.topic);
            detail = (TextView)itemView.findViewById(R.id.detail);
            mbtnlike=(ImageView)itemView.findViewById(R.id.likebut);
            mtxtlike=(TextView)itemView.findViewById(R.id.liketxt);
            likeamount=(TextView)itemView.findViewById(R.id.likeamount);
            mref=new Firebase("https://navigationdrawervideotut-611bf.firebaseio.com/Articals");

            mref.keepSynced(true);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
        public void setlike(final String postkey){

            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(postkey).hasChild(mAuth.getCurrentUser().getUid()))
                    {
                        mbtnlike.setImageResource(R.drawable.likeblue);
                        mtxtlike.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        mtxtlike.setTypeface(Typeface.DEFAULT_BOLD);
                    }else {
                        mbtnlike.setImageResource(R.drawable.like);
                        mtxtlike.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                        mtxtlike.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }

                    alike=mref.child(postkey);
                  alike.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {
                          if(dataSnapshot.hasChild("amount"))
                          likeamount.setText (dataSnapshot.child("amount").getValue(String.class));
                      }

                      @Override
                      public void onCancelled(FirebaseError firebaseError) {

                      }
                  });

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final ListItem listItem=listItems.get(i);
        Picasso.with(context)
                .load(listItem.getImageUrl())
        .into(viewHolder.itemImage );

         viewHolder.textViewHead.setText(listItem.getHead());
         viewHolder.textViewDesc.setText(listItem.getDesc());
         viewHolder.setlike(listItem.getHead());

         final int pos = i;
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You clicked"+listItem.getHead(),Toast.LENGTH_LONG ).show();
                //int pos= viewHolder.getAdapterPosition();
                if(pos!=RecyclerView.NO_POSITION)
                {

                   ListItem clickedDataItem=listItems.get(pos);
                    Intent intent=new Intent(context,Detail.class);
                    intent.putExtra("movie", listItems.get(pos).getHead());
                    intent.putExtra("story", listItems.get(pos).getDetails());
                    intent.putExtra("image", listItems.get(pos).getImageUrl());


                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }




            }

            private void overridePendingTransition(int pull_in_right, int pull_in_left) {
            }
        });
                     viewHolder.mbtnlike.setOnClickListener(new View.OnClickListener() {
                         int value;
                                     @Override
                                     public void onClick(View view)
                                     {
                                         final Firebase likecount=viewHolder.mref.child(listItem.getHead());
                                         likecount.addValueEventListener(new ValueEventListener() {
                                             @Override
                                             public void onDataChange(DataSnapshot dataSnapshot) {
                                               value=Integer.valueOf(dataSnapshot.child("amount").getValue(String.class));
                                             }

                                             @Override
                                             public void onCancelled(FirebaseError firebaseError) {

                                             }
                                         });



                                         mprocesslike=true;
                                         viewHolder.mref.addValueEventListener(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                             Firebase val=likecount.child("amount");
                                             if(mprocesslike) {

                                                 if (dataSnapshot.child(listItem.getHead()).hasChild(mAuth.getCurrentUser().getUid()))
                                                 {

                                                     viewHolder.mref.child(listItem.getHead()).child(mAuth.getCurrentUser().getUid()).removeValue();
                                                     mprocesslike = false;
                                                     value--;
                                                    val.setValue(String.valueOf(value));

                                                 }
                                                 else
                                                     {
                                                     viewHolder.mref.child(listItem.getHead()).child(mAuth.getCurrentUser().getUid()).setValue("Randomnumber");
                                                     mprocesslike = false;
                                                      value++;
                                                    val.setValue(String.valueOf(value));

                                                     }



                                             }


                                         }

                                         @Override
                                         public void onCancelled(FirebaseError firebaseError) {

                                         }
                                     });

                                         }


                     });

        viewHolder.mtxtlike.setOnClickListener(new View.OnClickListener() {
            int value;
            @Override
            public void onClick(View view) {

                final Firebase likecount=viewHolder.mref.child(listItem.getHead());
                likecount.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value=Integer.valueOf(dataSnapshot.child("amount").getValue(String.class));
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                mprocesslike=true;
                viewHolder.mref.addValueEventListener(new ValueEventListener() {
                    Firebase val=likecount.child("amount");
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(mprocesslike) {
                            if (dataSnapshot.child(listItem.getHead()).hasChild(mAuth.getCurrentUser().getUid()))
                            {
                                viewHolder.mref.child(listItem.getHead()).child(mAuth.getCurrentUser().getUid()).removeValue();
                                mprocesslike = false;
                                value--;
                                val.setValue(String.valueOf(value));

                            } else {
                                viewHolder.mref.child(listItem.getHead()).child(mAuth.getCurrentUser().getUid()).setValue("Randomnumber");
                                mprocesslike = false;
                                value++;
                                val.setValue(String.valueOf(value));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

    }


    @Override
    public int getItemCount() {

         return listItems.size();
    }
}