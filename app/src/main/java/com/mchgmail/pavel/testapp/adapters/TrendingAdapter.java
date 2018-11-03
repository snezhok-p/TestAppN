package com.mchgmail.pavel.testapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mchgmail.pavel.testapp.R;
import com.mchgmail.pavel.testapp.adapters.viewHolders.PictureViewHolder;

import java.util.ArrayList;

public class TrendingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<String> images;

    public TrendingAdapter(Context context, ArrayList<String> images){
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        String[] links = {images.get(i*3).replaceAll("\\\\", ""),
                images.get(i*3+1).replaceAll("\\\\", ""),
                images.get(i*3+2).replaceAll("\\\\", "")};
        Log.d("IMAGESSSS", images.get(i*3));
        Log.d("IMAGESSSS", images.get(i*3+1));
        Log.d("IMAGESSSS", images.get(i*3+2));
        return new PictureViewHolder(inflater.inflate(R.layout.layout_pictures, viewGroup,false),links,this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PictureViewHolder pictureViewHolder = (PictureViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return images.size()/3;
    }
}
