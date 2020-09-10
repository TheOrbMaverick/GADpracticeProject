package com.example.android.gadpracticeproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    LayoutInflater inflater;
    Context mContext;
    List<Learners> learners;


    public RecyclerViewAdapter(Context mContext, List<Learners> learners){

        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.learners = learners;
    }

    public void addData(List<Learners> data){
        learners.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View v = inflater.inflate(R.layout.item_learners,parent, false );
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.learnerName.setText(learners.get(position).getName());
        holder.learnerHours.setText(learners.get(position).getHours());
        holder.learnerCountry.setText(learners.get(position).getCountry());

        Picasso.with(mContext).load(learners.get(position).getBadgeUrl()).into(holder.learnerBadgeImage);

    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView learnerName, learnerHours, learnerCountry ;
        ImageView learnerBadgeImage;


        public MyViewHolder(View itemView) {
            super(itemView);

            learnerName = itemView.findViewById(R.id.learner_name);
            learnerHours = itemView.findViewById(R.id.learner_hours);
            learnerCountry = itemView.findViewById(R.id.learner_country);
            learnerBadgeImage = itemView.findViewById(R.id.learner_badge);

        }
    }
}
