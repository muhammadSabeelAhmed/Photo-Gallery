package com.sabeeldev.galleryapp.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImagesDao {

    @Insert
    void insertImages(Images images);

    @Update
    void updateImages(Images images);

    @Query("delete from images_table")
    void clearAllImages();

    @Query("select * from images_table")
    LiveData<List<Images>> showAllImages();
}
