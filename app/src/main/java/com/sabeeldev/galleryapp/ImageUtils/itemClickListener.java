package com.sabeeldev.galleryapp.ImageUtils;

import com.sabeeldev.galleryapp.RoomDatabase.Images;

import java.util.List;

public interface itemClickListener {

    void onPicClicked(PicHolder holder, int position, List<Images> pics);

    void onPicClicked(String pictureFolderPath, String folderName);
}
