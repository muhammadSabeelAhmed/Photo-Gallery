package com.sabeeldev.galleryapp.ImageUtils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sabeeldev.galleryapp.R;
import com.sabeeldev.galleryapp.RoomDatabase.Images;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.view.ViewCompat.setTransitionName;

public class picture_Adapter extends RecyclerView.Adapter<PicHolder> {

    private List<Images> pictureList = new ArrayList<>();
    private Context pictureContx;
    private itemClickListener picListerner;

    public picture_Adapter(Context pictureContx, itemClickListener picListerner) {
        this.pictureContx = pictureContx;
        this.picListerner = picListerner;
        notifyDataSetChanged();
    }

    public void setAdapter(List<Images> pictureList) {
        this.pictureList = pictureList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicHolder onCreateViewHolder(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View cell = inflater.inflate(R.layout.pic_holder_item, container, false);
        return new PicHolder(cell);
    }

    @Override
    public void onBindViewHolder(@NonNull final PicHolder holder, final int position) {

        final Images image = pictureList.get(position);

        Glide.with(pictureContx)
                .load(image.getPreviewURL())
                .apply(new RequestOptions().centerCrop())
                .into(holder.picture);

        setTransitionName(holder.picture, String.valueOf(position) + "_image");

        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picListerner.onPicClicked(holder, position, pictureList);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }
}
