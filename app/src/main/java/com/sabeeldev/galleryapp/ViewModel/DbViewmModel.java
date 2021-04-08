package com.sabeeldev.galleryapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sabeeldev.galleryapp.RoomDatabase.Images;
import com.sabeeldev.galleryapp.Repository.Repository;

import java.util.List;

public class DbViewmModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Images>> getImages;

    public DbViewmModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        getImages = repository.getAllImages();
    }

    public void insertImages(Images images) {
        repository.insertImages(images);
    }

    public void updateImages(Images images) {
        repository.updateImages(images);
    }

    public void clearImages() {
        repository.clearImages();
    }

    public LiveData<List<Images>> getAllImagess() {
        return getImages;
    }
}
