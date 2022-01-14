package com.webkeens.kutumbapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {


    private List<Repository> repositoryList;
    private Context context;

    private static int currentPosition = 0;

    public RepositoryAdapter(List<Repository> repositoryList, Context context) {
        this.repositoryList = repositoryList;
        this.context = context;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RepositoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RepositoryViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Repository repository = repositoryList.get(position);

        holder.textViewUserName.setText(repository.getUsername());
        holder.textViewRepositoryName.setText(repository.getRepositoryName());
        holder.textViewDetails.setText(repository.getDescription());
        holder.textViewLanguage.setText(repository.getLanguage());
        holder.textViewTotalStars.setText(String.valueOf(repository.getTotalStars()));
        holder.textViewForks.setText(String.valueOf(repository.getForks()));

        Glide.with(context).load(repository.getAvatar()).into(holder.imageViewAvatar);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slidedown);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewRepositoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName, textViewRepositoryName, textViewDetails, textViewLanguage,
                textViewTotalStars, textViewForks;
        ImageView imageViewAvatar;
        LinearLayout linearLayout;

        RepositoryViewHolder(View itemView) {
            super(itemView);

            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewRepositoryName = itemView.findViewById(R.id.textViewRepositoryName);
            textViewDetails = itemView.findViewById(R.id.textViewDetails);
            textViewLanguage = itemView.findViewById(R.id.textViewLanguage);
            textViewTotalStars = itemView.findViewById(R.id.textViewTotalStars);
            textViewForks = itemView.findViewById(R.id.textViewForks);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);

            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}