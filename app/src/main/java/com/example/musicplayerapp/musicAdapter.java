package com.example.musicplayerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class musicAdapter extends RecyclerView.Adapter<musicAdapter.ViewHolder> {

    ArrayList<MusicModel> songsList;
    Context context;

    public musicAdapter(ArrayList<MusicModel> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview,parent,false);
        return new musicAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
     MusicModel songData = songsList.get(position);
     holder.musicName.setText(songData.getTitle());


     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             MyMedia.getInstance().reset();
             MyMedia.currentIndex=position;
             Intent intent=new Intent(context,MusicPLayerActivity.class);
             intent.putExtra("List",songsList);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView musicName;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            musicName=itemView.findViewById(R.id.music_title);
            image=itemView.findViewById(R.id.icon);
        }
    }
}
