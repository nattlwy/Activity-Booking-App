package com.example.csis3175_grp6_proj;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportsIconRecyclerViewAdapter extends RecyclerView.Adapter<SportsIconRecyclerViewAdapter.ImageViewHolder> {
    List<SportsIcon> adapterList;
    OnItemClickListener onItemClickListener;
    public SportsIconRecyclerViewAdapter(List<SportsIcon> adapterList) {
        this.adapterList = adapterList;
    }

    public SportsIconRecyclerViewAdapter(List<SportsIcon> adapterList, OnItemClickListener onItemClickListener) {
        this.adapterList = adapterList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_sports,parent,false);
        ImageViewHolder holder = new ImageViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SportsIconRecyclerViewAdapter.ImageViewHolder holder, int position) {
        holder.imgViewItem
                .setImageResource(adapterList.get(position).getImgPic());
        holder.txtViewItem.setText(adapterList.get(position).getImgName());
        holder.txtViewItem.setGravity(Gravity.CENTER);
        holder.itemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewItem;
        TextView txtViewItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewItem = itemView.findViewById(R.id.sportsIcon);
            txtViewItem = itemView.findViewById(R.id.sportsIconName);
            imgViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    onItemClickListener.onItemClick(pos);
                    Log.d("icyfung", String.valueOf(pos));
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(int i);
    }
}
