package com.ruhulamin.callambulance.HelperClasses.DashboardAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruhulamin.callambulance.R;

import java.util.ArrayList;


public class MostRecentAdapter extends RecyclerView.Adapter<MostRecentAdapter.RecentViewHolder> {

    ArrayList<RecentHelperClass> recentMostViews;

    public MostRecentAdapter(ArrayList<RecentHelperClass> recentMostViews) {
        this.recentMostViews = recentMostViews;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_recent_card_design,parent,false);
        RecentViewHolder recentViewHolder = new RecentViewHolder(view);
        return recentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {

        RecentHelperClass recentHelperClass = recentMostViews.get(position);

        holder.image.setImageResource(recentHelperClass.getImage());
        holder.title.setText(recentHelperClass.getTitle());
        holder.desc.setText(recentHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return recentMostViews.size();
    }

    public static class RecentViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

       public RecentViewHolder(@NonNull View itemView) {
           super(itemView);

           //Hooks
           image = itemView.findViewById(R.id.mv_image);
           title = itemView.findViewById(R.id.mv_title);
           desc = itemView.findViewById(R.id.mv_desc);
       }
   }
}
