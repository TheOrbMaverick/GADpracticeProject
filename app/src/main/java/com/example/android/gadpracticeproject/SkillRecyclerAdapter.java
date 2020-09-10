package com.example.android.gadpracticeproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.SkillViewHolder>{

    LayoutInflater inflater;
    Context mContext;
    List<SkillIq> skillIq;

    public SkillRecyclerAdapter(Context mContext, List<SkillIq> skillIq){

        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.skillIq = skillIq;
    }


    public void addData(List<SkillIq> data){
        skillIq.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = inflater.inflate(R.layout.item_skill, parent, false );
        return new SkillViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {

        holder.skillName.setText(skillIq.get(position).getName());
        holder.skillScore.setText(skillIq.get(position).getScore());
        holder.skillCountry.setText(skillIq.get(position).getCountry());

        Picasso.with(mContext).load(skillIq.get(position).getBadgeUrl()).into(holder.skillBadgeImage);

    }

    @Override
    public int getItemCount() {
        return skillIq.size();
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder{

        TextView skillName, skillScore, skillCountry ;
        ImageView skillBadgeImage;


        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);


            skillName = itemView.findViewById(R.id.skillIQ_name);
            skillScore = itemView.findViewById(R.id.skillIQ_score);
            skillCountry = itemView.findViewById(R.id.skillIQ_country);
            skillBadgeImage = itemView.findViewById(R.id.skill_badge);
        }


    }




}
